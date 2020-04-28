package edu.iit.hawk.atolentino.services;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountManagerBoundary;
import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.account_management.interactors.AccountManager;
import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.reports.interactors.ProcessFactory;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchUtilities;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.JoinRequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.MessageInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.ProfileInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.RateInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.RideInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.UpdateJoinRequestInformationCreator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.response_generator.response_bodies.MessageHistoryResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.PostResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ReportResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.SearchAccountsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.SearchRidesResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ShareARideResponseBuilder;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ValidationErrorBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewAllReportsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewRatingsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewRideDetailResponseBody;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideManagerBoundary;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;
import edu.iit.hawk.atolentino.ride_management.interactors.RideManager;

import java.text.ParseException;
import java.util.Iterator;

//import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("")
public class ShareARide {
	AccountManagerBoundary accountManager = new AccountManager();
	RideManagerBoundary rideManager = new RideManager();	
	
	@Path("accounts")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(String body, @Context UriInfo uriInfo) {
		RequestInformationCreator profileInformationCreator = ProfileInformationCreator.NEW_ACCOUNT();
		RequestInformation profileInformation = profileInformationCreator.create(body);
		
		if (profileInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							profileInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();	
		}
		
		if (accountManager.isPhoneNumberInUse(profileInformation)) {
			String detail = ResponseBodyUtility.INVALID_PHONE_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
				
		int aid = accountManager.addAccount(profileInformation);
		return new ShareARideResponseBuilder(Status.CREATED)
				.addResourceLocationHeader(Integer.toString(aid), uriInfo)
				.addResponseBody(new PostResponseBody(
						ResponseBodyUtility.ACCOUNT_ID_KEY,
						aid))
						.build();
	}
	
	@Path("accounts/{aid}/status")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response activateAccount(@PathParam("aid") int aid, String body, @Context UriInfo uriInfo) {
		if (!accountManager.doesAccountExist(aid)) {
			String detail = ResponseBodyUtility.invalidAccountErrorMessage(aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
			}

		if (accountManager.isAccountActive(aid)) {
			String detail = ResponseBodyUtility.ACCOUNT_ALREADY_ACTIVE_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
			}
		
		RequestInformationCreator profileInformationCreator = ProfileInformationCreator.ACTIVE_ACCOUNT();
		RequestInformation profileInformation = profileInformationCreator.create(body);
		
		if (profileInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							profileInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
			}
		
		accountManager.updateAccount(aid, profileInformation);
		return new ShareARideResponseBuilder(Status.NO_CONTENT).build();
	}
	
	@Path("accounts/{aid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("aid") int aid, String body, @Context UriInfo uriInfo) {
		if (!accountManager.doesAccountExist(aid)) {
			String detail = ResponseBodyUtility.invalidAccountErrorMessage(aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
			}
		
		RequestInformationCreator profileInformationCreator;
		
		if (accountManager.isAccountActive(aid)) {
			profileInformationCreator = new ProfileInformationCreator(true);
		}
		else {
			profileInformationCreator = new ProfileInformationCreator(false);
		}
		
		RequestInformation profileInformation = profileInformationCreator.create(body);
		if (profileInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							profileInformation, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();			
			}
		accountManager.updateAccount(aid, profileInformation);
		return new ShareARideResponseBuilder(Status.NO_CONTENT).build();
	}
	
	@Path("accounts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchAccounts(@DefaultValue("") @QueryParam("key") String key) {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts(key);
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new SearchAccountsResponseBody(iterator))
				.build();
	}
	
	@Path("accounts/{aid}/ratings")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rateAccount(@PathParam("aid") int aid, String body, @Context UriInfo uriInfo) {
		RequestInformationCreator rateInformationCreator = new RateInformationCreator();
		RequestInformation rateInformation = rateInformationCreator.create(body);
		
		if (rateInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							rateInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build(); 
		}
		
		int raterAid = rateInformation.getAid();
		if (aid == raterAid) {
			String detail = ResponseBodyUtility.ACCOUNT_RATE_THEMSELF_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();	
		}
		
		if (!accountManager.doesAccountExist(aid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		int rid = rateInformation.getRid();
		if (!rideManager.doesRideExist(rid)) {
			String detail = ResponseBodyUtility.createNonExistingRideErrorMessage(rid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
		}
		
		if (!rideManager.isAccountDriverOfRide(rid, aid) && !rideManager.isAccountRiderOfRide(rid, aid)) {
			String detail = ResponseBodyUtility.createNonRideParticipantRatingErrorMessage(rid, aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
		}
		
		if (!rideManager.isAccountDriverOfRide(rid, raterAid) && !rideManager.isAccountRiderOfRide(rid, raterAid)) {
			String detail = ResponseBodyUtility.createNonRideParticipantRatingErrorMessage(rid, raterAid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();		
		}
		
		String date = rideManager.getRideDate(rid);
		int sid;
		if (rideManager.isAccountDriverOfRide(rid, aid)) {
			sid = accountManager.rateDriver(aid, date, rateInformation);
		}
		
		else {
			sid = accountManager.rateRider(aid, date, rateInformation);
			accountManager.incrementRiderRideParticipation(aid);
		}
		
		return new ShareARideResponseBuilder(Status.CREATED)
				.addResourceLocationHeader(Integer.toString(sid), uriInfo)
				.addResponseBody(new PostResponseBody(
						ResponseBodyUtility.RATING_ID_KEY,
						sid))
						.build();
	}
	
	@Path("accounts/{aid}/driver")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response viewDriverRatings(@PathParam("aid") int aid) {
		if (!accountManager.doesAccountExist(aid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new ViewRatingsResponseBody(
						accountManager.getAccount(aid), 
						ViewRatingsResponseBody.RatingType.DRIVER))
						.build();
	}
	
	@Path("accounts/{aid}/rider")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response viewRiderRatings(@PathParam("aid") int aid) {
		if (!accountManager.doesAccountExist(aid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new ViewRatingsResponseBody(
						accountManager.getAccount(aid), 
						ViewRatingsResponseBody.RatingType.RIDER))
						.build();
	}
	
	
	@Path("rides")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRide(String body, @Context UriInfo uriInfo) {
		RequestInformationCreator rideInformationCreator = new RideInformationCreator();
		RequestInformation rideInformation = rideInformationCreator.create(body);
		
		if (rideInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							rideInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		int aid = rideInformation.getAid();
		if (!accountManager.isAccountActive(aid)) {
			String detail = ResponseBodyUtility.inactiveAccountCreateRideErrorMessage(aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		
		int rid = rideManager.addRide(rideInformation);
		return new ShareARideResponseBuilder(Status.CREATED)
				.addResourceLocationHeader(Integer.toString(rid), uriInfo)
				.addResponseBody(new PostResponseBody(
						ResponseBodyUtility.RIDE_ID_KEY,
						rid))
						.build();
	}
	
	@Path("rides/{rid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRide(@PathParam("rid") int rid, String body, @Context UriInfo uriInfo) {
		RequestInformationCreator rideInformationCreator = new RideInformationCreator();
		RequestInformation rideInformation = rideInformationCreator.create(body);
		
		if (rideInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							rideInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (!rideManager.doesRideExist(rid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		int aid = rideInformation.getAid();
		if (!rideManager.isAccountDriverOfRide(rid, aid)) {
			String detail = ResponseBodyUtility.ACCOUNT_INVALID_CREATOR_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		rideManager.updateRide(rid, rideInformation);
		return new ShareARideResponseBuilder(Status.NO_CONTENT).build();
	}
	
	@Path("rides/{rid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRide(@PathParam("rid") int rid, @Context UriInfo uriInfo) {
		if (rideManager.doesRideExist(rid)) {
			rideManager.deleteRide(rid);
			return new ShareARideResponseBuilder(Status.NO_CONTENT).build();
		}
		return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
	}
	
	@Path("rides")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchRides(
			@DefaultValue("") @QueryParam("from") String fromKey, 
			@DefaultValue("") @QueryParam("to") String toKey,
			@DefaultValue("") @QueryParam("date") String dateKey) {
		Iterator<RideUtilities> iterator = rideManager.searchRide(fromKey, toKey, dateKey);
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new SearchRidesResponseBody(iterator))
				.build();
	}
	
	@Path("rides/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewRideDetail(@PathParam("rid") int rid) {
		if (!rideManager.doesRideExist(rid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		RideUtilities ride = rideManager.getRide(rid);
		int aid = ride.getAid();
		AccountUtilities account = accountManager.getAccount(aid);
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new ViewRideDetailResponseBody(
						ride, 
						account))
						.build();
	}
	
	@Path("rides/{rid}/join_requests")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createJoinRequest(@PathParam("rid") int rid, String body, @Context UriInfo uriInfo) {
		RequestInformationCreator joinRequestInformationCreator = new JoinRequestInformationCreator();
		RequestInformation joinRequestInformation = joinRequestInformationCreator.create(body);
		
		if (joinRequestInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							joinRequestInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		int aid = joinRequestInformation.getAid();
		if (!accountManager.isAccountActive(aid)) {
			String detail = ResponseBodyUtility.inactiveAccountCreateJoinRequestErrorMessage(aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (!rideManager.doesRideExist(rid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		if (rideManager.sentJoinRequestToRide(rid, aid)) {
			String detail = ResponseBodyUtility.createSentJoinRequestAlreadyErrorMessage(rid, aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (rideManager.isAccountDriverOfRide(rid, aid)) {
			String detail = ResponseBodyUtility.ACCOUNT_CREATOR_JOIN_REQUEST_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		int jid = rideManager.addJoinRequest(rid, joinRequestInformation);
		return new ShareARideResponseBuilder(Status.CREATED)
				.addResourceLocationHeader(Integer.toString(jid), uriInfo)
				.addResponseBody(new PostResponseBody(
						ResponseBodyUtility.JOIN_RIDE_ID_REQUEST_KEY,
						jid))
						.build();
	}
	
	@Path("rides/{rid}/join_requests/{jid}")
	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJoinRequest(
			@PathParam("rid") int rid,
			@PathParam("jid") int jid,
			String body,
			@Context UriInfo uriInfo
			) {
		RequestInformationCreator updateJoinRequestInformationCreator = new UpdateJoinRequestInformationCreator();
		RequestInformation updateJoinRequestInformation = updateJoinRequestInformationCreator.create(body);
		
		if (updateJoinRequestInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							updateJoinRequestInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (!rideManager.doesRideExist(rid) || !rideManager.doesJoinRequestExist(rid, jid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		
		PatchUtilities updateJoinRequestPatch = (PatchUtilities) updateJoinRequestInformation;
		int aid = updateJoinRequestInformation.getAid();
		if (updateJoinRequestPatch.getType() == PatchType.RIDE_CONFIRMED) {
			if (rideManager.isAccountDriverOfRide(rid, aid)) {
				accountManager.incrementDriverRideParticipation(aid);
				int riderAid = rideManager.getJoinRequestCreatorId(rid, jid);
				accountManager.incrementDriverRideParticipation(riderAid);
				rideManager.updateRideConfirmed(rid, jid, updateJoinRequestInformation);
				return new ShareARideResponseBuilder(Status.OK).build();
			}
			
			String detail = ResponseBodyUtility.invalidAccountRideConfirmedErrorMessage(rid, aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		else if (updateJoinRequestPatch.getType() == PatchType.PICKUP_CONFIRMED) {
			if (rideManager.isAccountRiderOfRide(rid, aid)) {
				rideManager.updatePickUpConfirmed(rid, jid);
				return new ShareARideResponseBuilder(Status.OK).build();
			}
			
			String detail = ResponseBodyUtility.invalidAccountPickUpConfirmedErrorMessage(rid, aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		return new ShareARideResponseBuilder(Status.INTERNAL_SERVER_ERROR).build();	
	}
	
	@Path("rides/{rid}/messages")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(@PathParam("rid") int rid, String body, @Context UriInfo uriInfo) {
		RequestInformationCreator messageInformationCreator = new MessageInformationCreator();
		RequestInformation messageInformation = messageInformationCreator.create(body);
		
		if (messageInformation.isNill()) {
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							messageInformation,
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (!rideManager.doesRideExist(rid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		
		int aid = messageInformation.getAid();
		if (!accountManager.doesAccountExist(aid)) {
			String detail = ResponseBodyUtility.invalidAccountErrorMessage(aid);
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		if (!accountManager.isAccountActive(aid)) {
			String detail = ResponseBodyUtility.ACCOUNT_NOT_ACTIVE_ERROR_MESSAGE;
			return new ShareARideResponseBuilder(Status.BAD_REQUEST)
					.addResponseBody(new ValidationErrorBody(
							detail, 
							uriInfo, 
							Status.BAD_REQUEST.getStatusCode()))
							.build();
		}
		
		int mid = rideManager.addMessage(rid, messageInformation);
		return new ShareARideResponseBuilder(Status.CREATED)
				.addResourceLocationHeader(Integer.toString(mid), uriInfo)
				.addResponseBody(new PostResponseBody(
						ResponseBodyUtility.MESSAGE_ID_KEY,
						mid))
						.build();	
	}
	
	@Path("rides/{rid}/messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response viewMessageHistory(@PathParam("rid") int rid) {
		if (!rideManager.doesRideExist(rid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		Iterator<Message> iterator = rideManager.getMessages(rid);
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new MessageHistoryResponseBody(
						iterator))
						.build();
	}
	
	@Path("reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllReports() {
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new ViewAllReportsResponseBody())
				.build();
	}
	
	@Path("reports/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport(
			@PathParam("pid") int pid, 
			@DefaultValue("") @QueryParam("start_date") String startDate,
			@DefaultValue("") @QueryParam("end_date") String endDate
			) {
		if (!ShareARideProcess.doesProcessExist(pid)) {
			return new ShareARideResponseBuilder(Status.NOT_FOUND).build();
		}
		
		Iterator<RideUtilities> rides = null;
		try {
			rides = rideManager.getRidesBetweenStartDateAndEndDate(startDate, endDate);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return new ShareARideResponseBuilder(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		ProcessFactory processFactory = new ProcessFactory(startDate, endDate, rides);
		ShareARideProcess process = processFactory.buildProcess(pid);
		return new ShareARideResponseBuilder(Status.OK)
				.addResponseBody(new ReportResponseBody(process))
				.build();
	}
}