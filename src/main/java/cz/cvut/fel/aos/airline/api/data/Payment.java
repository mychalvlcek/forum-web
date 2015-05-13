package cz.cvut.fel.aos.airline.api.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Payment DTO represents database object with specified values.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
@XmlRootElement
public class Payment extends AbstractDto {

    @XmlElement(name = "bank-account")
    private String bankAccount;

	/**
	 * Returns the number of the bank account.
	 * @return String
	 */
    public String getBankAccount() {
        return bankAccount;
    }

	/**
	 * Set bank account
	 * @param bankAccount number of the bank account
	 */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}

