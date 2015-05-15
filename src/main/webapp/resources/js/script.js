// init
$(document).ready(function () {
    // init values
    sessionStorage.setItem('base', 10);
    sessionStorage.setItem('offset', 0);
    sessionStorage.setItem('sort', 'id');
    sessionStorage.setItem('order', 'asc');

    // hash change handling
    $(window).on("hashchange", handleHashChange);
    $(window).trigger('hashchange');

    // ajax, page loader
    $("#loader").fadeOut('fast');
    $(document).ajaxStart(function () {
        $("#loader").fadeIn(30);
    }).ajaxStop(function () {
        $("#loader").fadeOut('fast');
    });

    // scroll to top button
    $("a[href=#top]").click(function() {
        $("html, body").animate({ scrollTop: 0 }, 200);
        return false;
    });

    // form validation & save
    $('form').bootstrapValidator({}).on('success.form.bv', function(e) {
        e.preventDefault();
        $('.modal').modal('hide');
        var data = getJSON($(e.target).serializeArray());
        if (data.id != '') {
            update(data);
        } else {
            create(data);
        }
    });

    // clear storage (set defaults)
    $('a[data-clear]').on('click', function() {
        sessionStorage.clear();
        sessionStorage.setItem('base', 10);
        sessionStorage.setItem('offset', 0);
        sessionStorage.setItem('sort', 'id');
        sessionStorage.setItem('order', 'asc');
    });

    // sort links
    $('a.sort').on('click', function(e) {
        e.preventDefault();

        sessionStorage.setItem('sort', $(this).attr('data-sort'));
        sessionStorage.setItem('order', $(this).attr('data-order'));

        if ($(this).attr('data-order') == 'asc') {
            $(this).attr('data-order', 'desc');
        } else {
            $(this).attr('data-order', 'asc');
        }

        $(window).trigger('hashchange');

        $('a.sort').removeClass('active');
        $(this).addClass('active');
    });

    // pagination
    $('div.paginate').on('click', 'a', function(e) {
        e.preventDefault();

        sessionStorage.setItem('offset', $(this).attr('data-offset'));
        $(window).trigger('hashchange');
    });

    // add buttons
    $('body').on('click', 'a[data-add]', function(e) {
        e.preventDefault();
        var modalId = $(this).attr('data-target');
        $(modalId).modal('show');
        $(modalId).find('form').find("input[name!='url'], textarea").val(""); // reset
        $(modalId).find('form').find("input[name='url']").val($(this).attr('data-add')); // set correct url

    });

    // edit buttons
    $('body').on('click', 'a[data-edit]', function(e) {
        e.preventDefault();
        var modalId = $(this).attr('data-target');
        $(modalId).modal('show');
        get('/forum/rest' + $(this).attr('data-url'), $(modalId).find('form'));

    });

    // delete buttons
    $('body').on('click', 'a[data-remove]', function(e) {
        e.preventDefault();
        if (window.confirm("Do you really want to remove item?")) {
            remove($(this).attr('data-remove'));
        }

    });

    // cancel buttons
    $('body').on('click', 'a[data-cancel]', function(e) {
        e.preventDefault();
        if (window.confirm("Rlly?")) {
            var headers = getAuthorizationHeader();
            headers['X-Password'] = $(this).attr('data-password');
            performAction($(this).attr('data-cancel'), 'PUT', {}, headers);
        }

    });

    // pay buttons
    $('body').on('click', 'a[data-pay]', function(e) {
        e.preventDefault();
        if (window.confirm("Rlly?")) {
            performAction($(this).attr('data-pay'), 'POST', {"bank-account": "0000-0000-0000-0000"});
        }

    });

	var wsUri = getRootUri() + "/forum/counter";

	function getRootUri() {
		return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" +
				(document.location.port == "" ? "8080" : document.location.port);
	}

	var websocket = new WebSocket(wsUri);

	websocket.onopen = function(evt) {
//		console.log('open');
//		console.log(evt);
	};
	websocket.onmessage = function(evt) {
		$('.user-counter').html(evt.data);
	};
	websocket.onerror = function(evt) {
//		console.log('error');
//		console.log(evt);
	};

	window.onbeforeunload = function(e) {
		websocket.onclose = function () {};
		websocket.close();
	};

});

/**
 * Dispatcher.
 * Dispatch all "requests" (hash changes) to corresponding handler.
 */
function handleHashChange() {

    switch (window.location.hash.substring(1)) {
        case 'destinations':
            getAll('/rest/destination/', '#destinations', '#destination', ['id', 'name', 'lat', 'lon', 'url']);
            break;
        case 'flights':
            getAll('/forum/rest/flight/', '#flights', '#flight', ['id', 'dateOfDeparture', 'name', 'from', 'to', 'distance', 'price', 'seats', 'url']);
            break;
        case 'reservations':
            getAll('/forum/rest/reservation/', '#reservations', '#reservation', ['id', 'created', 'flight', 'password', 'seats', 'state', 'url']);
            break;
        default:
            break;
    }
}

/**
 * Generic save function.
 * Saves data from submitted form.
 * @param data
 */
function create(data) {
    var url = data.url;

    delete data.url;

    $.ajax({
        url: '/forum/rest' + url,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        headers: {'Authorization': 'Basic ' + btoa('root:root')},
        success: function (response) {
            $(window).trigger('hashchange');
            console.log('created: ', data);
            showAlert('success', '<strong>' + response + '</strong>');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = ("responseJSON" in xhr ? xhr.responseJSON : xhr.statusText);
            showAlert('danger', 'HTTP: <strong>' + xhr.status + '</strong> ' + error);
        }
    });
}

/**
 * Generic update function.
 * Updates data from submitted form.
 * @param data
 */
function update(data) {
    var url = data.url;

    delete data.url;

    $.ajax({
        url: '/forum/rest' + url,
        type: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        headers: headers,
        success: function (response) {
            $(window).trigger('hashchange');
            console.log('updated: ', data);
            showAlert('success', '<strong>' + response + '</strong>');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = ("responseJSON" in xhr ? xhr.responseJSON : xhr.statusText);
            showAlert('danger', 'HTTP: <strong>' + xhr.status + '</strong> ' + error);
        }
    });
}

/**
 * Generic data remover.
 * Removes entity specified by url
 * @param url
 */
function remove(url) {
    $.ajax({
        url: '/forum/rest' + url,
        type: 'DELETE',
        dataType: 'json',
        success: function (response) {
            $(window).trigger('hashchange');
            console.log('removed: ', url);
            showAlert('success', '<strong>' + response + '</strong>');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = ("responseJSON" in xhr ? xhr.responseJSON : xhr.statusText);
            showAlert('danger', 'HTTP: <strong>' + xhr.status + '</strong> ' + error);
        }
    });
}

/**
 * Action performer
 * Performs specified url
 * @param url
 */
function performAction(url, method, data, hdrs) {
    data = data || {};
    headers = hdrs || {};

    $.ajax({
        url: '/forum/rest' + url,
        type: method,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        headers: headers,
        success: function (response) {
            $(window).trigger('hashchange');
            showAlert('success', '<strong>' + response + '</strong>');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr, ajaxOptions, thrownError);
            var error = ("responseJSON" in xhr ? xhr.responseJSON : xhr.statusText);
            showAlert('danger', 'HTTP: <strong>' + xhr.status + '</strong> ' + error);
        }
    });
}

/**
 * Generic data retriever.
 * Gets data and render to table given by selector
 * @param url
 * @param selector selector of node to draw the data
 * @param modalSelector selector of (edit) modal
 * @param fields
 */
function getAll(url, selector, modalSelector, fields) {
    headers = getCustomHeaders();

    $.ajax({
        url: url,
        type: 'GET',
        headers: headers,
        dataType: 'json',
        success: function (data, status, request) {
            sessionStorage.setItem('count', request.getResponseHeader('X-Count-records'));

            var table = $(selector).find('tbody');
            table.html('');
            for (row in data) {
                var tr = $('<tr></tr>');

                for (field of fields) {
                    var td = $('<td>' + data[row][field] + '</td>');
                    tr.append(td);
                }
                // actions
                if (modalSelector == '#reservation')
                {
                    var td = $('<td>'
                        + '<a href="#" class="btn btn-danger btn-xs" data-cancel="' + data[row]['url'] + '" data-password="' + data[row]['password'] + '">Cancel</a> '
                        + '<a href="#" class="btn btn-success btn-xs" data-pay="' + data[row]['url'] + '/payment' + '" data-password="' + data[row]['password'] + '">Pay</a> '
                        // + '<a href="#" class="btn btn-danger btn-xs" data-remove="' + data[row]['url'] + '">Remove</a>'
                        + '</td>');
                } else {
                    var td = $('<td>'
                        + '<a href="#" class="btn btn-primary btn-xs" data-edit="true" data-url="' + data[row]['url'] + '" data-target="' + modalSelector + '">Edit</a> '
                        + '<a href="#" class="btn btn-danger btn-xs" data-remove="' + data[row]['url'] + '">Remove</a>'
                        + '</td>');
                }
                tr.append(td);

                table.append(tr);
            }

            // paging
            $(selector).find('div.paginate').html('');
            if (parseInt(sessionStorage.getItem('count')) > parseInt(sessionStorage.getItem('base')))
            {
                var paging = getPaging(sessionStorage.getItem('offset')/sessionStorage.getItem('base') + 1, sessionStorage.getItem('base'), sessionStorage.getItem('count'));
                $(selector).find('div.paginate').append(paging);
            }
        }
    });
}

/**
 * Generic data retriever.
 * Gets entity specified by url
 * @param url
 * @param form
 */
function get(url, form) {
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log('retrieved: ', data);
            populate(form, data);
        }
    });
}

/**
 * Render alert block to page.
 * @param level
 * @param text
 */
function showAlert(level, text) {
    var alert = $('<div class="alert alert-dismissable alert-' + level + '"> '
        + '<button type="button" class="close" data-dismiss="alert">×</button>'
        + text + '</div>');
    $("#alerts").append(alert);
    alert.delay(3000).fadeOut(500);
}

/**
 * Converts form serialized data into json structure.
 * @param data
 */
function getJSON(data) {
    var jsonData = {};
    $.each(data, function() {
        if (jsonData[this.name]) {
            if (!jsonData[this.name].push) {
                jsonData[this.name] = [jsonData[this.name]];
            }
            jsonData[this.name].push(this.value || '');
        } else {
            jsonData[this.name] = this.value || '';
        }
    });
    return jsonData;
}

/**
 * Popupates the given form with JSON data
 * @param frm form object
 * @param data JSON data
 */
function populate(frm, data) {
    $.each(data, function(key, value){
        var $ctrl = $('[name='+key+']', frm);
        switch($ctrl.attr("type"))
        {
            case "text" :
            case "hidden":
                $ctrl.val(value);
                break;
            case "radio" : case "checkbox":
            $ctrl.each(function(){
                if($(this).attr('value') == value) {  $(this).attr("checked",value); } });
            break;
            default:
                $ctrl.val(value);
        }
    });
}

/**
 * Get custom headers (order|sort|filter|paging)
 * @returns {{}}
 */
function getCustomHeaders()
{
    var hdrs = {};

    // sorting
    hdrs['X-Order'] = sessionStorage.getItem('sort') + ':' + sessionStorage.getItem('order');

    // paging
    hdrs['X-Base'] = sessionStorage.getItem('base');
    hdrs['X-Offset'] = sessionStorage.getItem('offset');
    hdrs['Authorization'] = 'Basic ' + btoa('root:root');
    return hdrs;
}

function getAuthorizationHeader()
{
    return {'Authorization' : 'Basic ' + btoa('root:root')};
}

/**
 *
 * @param page
 * @param perPage
 * @param count
 * @returns {*|jQuery|HTMLElement}
 */
function getPaging(page, perPage, count)
{
    var paging = $('<ul class="pagination pagination-sm"></ul>');
    if (page == 1)
    {
        paging.append('<li class="disabled"><a href="#">«</a></li>');
    } else {
        paging.append('<li><a href="#" data-offset="' + ((page-2)*perPage) + '">«</a></li>');
    }

    for (var i = 1; i <= Math.ceil(count / perPage); i++)
    {
        if (page == i)
        {
            paging.append('<li class="active"><a href="#" data-offset="' + ((i-1)*perPage) + '">' + i + '</a></li>');
        }
        else
        {
            paging.append('<li><a href="#" data-offset="' + ((i-1)*perPage) + '">' + i + '</a></li>');
        }
    }
    if (page == i-1)
    {
        paging.append('<li class="disabled"><a href="#">»</a></li>');
    } else {
        paging.append('<li><a href="#" data-offset="' + (page*perPage) + '">»</a></li>');
    }
    return paging;
}
