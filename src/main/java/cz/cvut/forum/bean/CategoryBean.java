package cz.cvut.forum.bean;

//import cz.wa2.poll.frontend.dto.PollDTO;
//import cz.wa2.poll.frontend.dto.VoterGroupDTO;
//import cz.wa2.poll.frontend.exception.ClientException;
//import cz.wa2.poll.frontend.rest.PollClient;
//import cz.wa2.poll.frontend.rest.VoterClient;
//import cz.wa2.poll.frontend.rest.VoterGroupClient;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.GenericType;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
import cz.cvut.forum.dto.CategoryDTO;

@ManagedBean
@ViewScoped
public class CategoryBean {

    private Long id;
    private CategoryDTO category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

//    public void init() throws Exception {
//        try {
//            System.out.println("asd");
////            category = categoryService.getCategoryById(id);
//        } catch(Exception e) {
//            throw new Exception("Kategorie s id: " + id + " nenalezena.");
//        }
//    }

//    private VoterClient voterClient;
//    private PollClient pollClient;
//    private List<PollDTO> pollDTOs;

//    @ManagedProperty(value="#{voter}")
//    LoggedVoter loggedVoter;

    @PostConstruct
    public void init() {
        System.out.println("init");
//        category = Client.create().resource("http://localhost:8080/api/category/").path("" + id).accept("application/json").get(CategoryDTO.class);

//        Client client = Client.create();
//
//        WebResource webResource = client
//                .resource("http://localhost:8080/api/category/");
//
//        ClientResponse response = webResource.accept("application/json")
//                .get(ClientResponse.class);
//
//        String output = response.getEntity(String.class);
//
//        System.out.println("Output from Server .... \n");
//        System.out.println(output);

//        Client client = Client.create();
//        WebResource webResource = client.resource("http://maps.googleapis.com/maps/api/geocode/json");
////        List<CategoryDTO> response = webResource.accept("application/json").get(new GenericType<List<CategoryDTO>>() {});
//        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//        }
//        List<CategoryDTO> x = response.getEntity(new GenericType<List<CategoryDTO>>() {});

//        InputStream is = response.getEntityInputStream();
//        int ch;
//        StringBuilder sb = new StringBuilder();
//        try {
//            while((ch = is.read())!= -1)
//                sb.append((char)ch);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(sb.toString());
//        if (element.isJsonObject()) {
//
//            JsonObject location = element.getAsJsonObject()
//                    .get("results").getAsJsonArray()
//                    .get(0).getAsJsonObject()
//                    .get("geometry").getAsJsonObject().get("location").getAsJsonObject();
//            return new GPS(location.get("lat").getAsDouble(), location.get("lng").getAsDouble());
//        }


//        Client restClient = ClientBuilder.newClient();;
//        WebTarget target;
//
//        target = ClientStore.getClient().target(address).path("/rest/voter");
//
//        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
//        Response response = invocationBuilder.get();
//        int status = response.getStatus();
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("getVoters.status = " + status);
//        }
//
//        if (status == 200) {
//            List<VoterDTO> voters = response.readEntity(new GenericType<List<VoterDTO>>() {
//            });
//            response.close();
//            return voters;
//        } else {
//            return new ArrayList<VoterDTO>();
//        }

    }
        //////


        //    WebTarget resourceTarget = target.path("/" + id).path("/group").queryParam("findWho",findWho);
//    Invocation.Builder invocationBuilder = resourceTarget.request(MediaType.APPLICATION_JSON_TYPE);
//    Response response = invocationBuilder.get();
//    int status = response.getStatus();
//
//    if (logger.isDebugEnabled()) {
//        logger.debug("geGroups.status = " + status);
//    }
//
//    if (status == 200) {
//        List<VoterGroupDTO> voters = response.readEntity(new GenericType<List<VoterGroupDTO>>() {
//        });
//        response.close();
//        return voters;
//    } else {
//        return new ArrayList<VoterGroupDTO>();
//    }

//    private GPS getGps(String destination) {
//        Client client = Client.create();
//        WebResource webResource = client.resource("http://maps.googleapis.com/maps/api/geocode/json")
//                .queryParam("address", destination).queryParam("sensor", "false");
//        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//        }
//
//        InputStream is = response.getEntityInputStream();
//        int ch;
//        StringBuilder sb = new StringBuilder();
//        try {
//            while((ch = is.read())!= -1)
//                sb.append((char)ch);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(sb.toString());
//        if (element.isJsonObject()) {
//
//            JsonObject location = element.getAsJsonObject()
//                    .get("results").getAsJsonArray()
//                    .get(0).getAsJsonObject()
//                    .get("geometry").getAsJsonObject().get("location").getAsJsonObject();
//            return new GPS(location.get("lat").getAsDouble(), location.get("lng").getAsDouble());
//        }
//        return null;
//    }


//        voterClient = new VoterClient(loggedVoter.getRestServerAddress());
//        pollClient = new PollClient(loggedVoter.getRestServerAddress());
//
//        String path = ((HttpServletRequest) FacesContext.getCurrentInstance()
//                .getExternalContext().getRequest()).getRequestURI();
//        if(path.equals("/unvoted-polls.xhtml")){
//            pollDTOs = voterClient.getNonvotedPolls(loggedVoter.getVoter().getId());
//        }
//        if(path.equals("/voted-polls.xhtml")){
//            pollDTOs = voterClient.getVotedPolls(loggedVoter.getVoter().getId());
//        }
//        if(path.equals("/supervised-polls.xhtml")){
//            pollDTOs = voterClient.getSupervisedPolls(loggedVoter.getVoter().getId());
//        }

//    public String vote(PollDTO pollDTO){
//        loggedVoter.setPollDTO(pollDTO);
//        loggedVoter.setBallotDTO(voterClient.getBallot(loggedVoter.getVoter().getId(), pollDTO.getId()));
//        return "success";
//    }
//
//    public String results(PollDTO pollDTO){
//        loggedVoter.setPollDTO(pollDTO);
//        return "success";
//    }
//
//    public String deletePoll(PollDTO pollDTO){
//        try {
//            pollClient.deletePoll(pollDTO.getId());
//        } catch (ClientException e) {
//            addMessage(FacesMessage.SEVERITY_ERROR,"Error",e.getMessage());
//            return null;
//        }
//        return "success";
//    }
//
//    public List<PollDTO> getPollDTOs() {
//        return pollDTOs;
//    }
//
//    public LoggedVoter getLoggedVoter() {
//        return loggedVoter;
//    }
//
//    public void setLoggedVoter(LoggedVoter loggedVoter) {
//        this.loggedVoter = loggedVoter;
//    }
}
