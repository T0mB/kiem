package main.java.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import main.java.dao.RecordingDAO;
import main.java.domain.Recording;

@Path("/recordingservice")
public class RecordingService {

	private RecordingDAO rdao = new RecordingDAO();

	@Path("/getdataforhouse/{database}/{huisid}")
	@GET
	@Produces("application/json")
	public Response getDataForHouse( @PathParam("database") String database, @PathParam("huisid") int huisid)
			throws JSONException, IOException, ClassNotFoundException {

		JSONObject jsonObject = new JSONObject();
		List<Recording> list = new ArrayList<Recording>();
		list = rdao.retrieveDataForHouse(huisid, database);

//		String check = "no data";
//
//		if (!list.isEmpty()) {
//			check = "";
//			for (int i = 0; i < list.size(); i++) {
//				jsonObject.put("Data", list.get(i).getData());
//				jsonObject.put("Type", list.get(i).getType());
//
//				check += jsonObject;
//			}
//		}
		
		String json = new Gson().toJson(list);

		return Response.status(200).entity(json).build();
	}

	//depending on how many times data gets send to the 
	//database use this method and at the start of a day
	//create a new record with insertIntoSensor method
	@Path("/insertData/{database}/{idrecording}/{newdata}")
	@PUT
	@Produces("application/json")
	public Response insertData( @PathParam("database") String database, @PathParam("idredcording") int idrecording, @PathParam("newdata") String newdata) {

		boolean bool = false;
		String fail = "failure";

		bool = rdao.insertData(idrecording, newdata, database);

		if (bool) {
			String check = "data inserted";
			return Response.status(200).entity(check).build();
		}
		return Response.status(409).entity(fail).build();
	}

	
	//TODO find out if a record ID is necessary
	@Path("/insertIntoSensor/{database}+{idrecording}+{newdata}+{DateOfRec}+{huisid}")
	@POST
	@Produces("application/json")
	public Response insertIntoSensor( @PathParam("database") String database, @PathParam("idrecording") int idrecording, @PathParam("newdata") String newdata,
			@PathParam("DateOfRec") Date date, @PathParam("huisid") int huisid) {

		boolean bool = false;
		String fail = "failure";

		bool = rdao.insertIntoRecording(database,idrecording, newdata,date, huisid);

		if (bool) {
			String check = "data inserted";
			return Response.status(200).entity(check).build();
		}
		
		return Response.status(409).entity(fail).build();
	}

}
