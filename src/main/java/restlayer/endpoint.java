package restlayer;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qa.persistence.repository.TransactionalRepo;

@Path("/Accounts")
public class endpoint {

	@Inject
	private TransactionalRepo service;

	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String account) {
		return service.createAccount(account);
	}

	@Path("/deleteAccount")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(int accountNumber) {
		return service.deleteAccount(accountNumber);
	}
}