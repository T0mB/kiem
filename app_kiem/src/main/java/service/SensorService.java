package main.java.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
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

import main.java.dao.SensorDAO;
import main.java.domain.Sensor;

@Path("/sensorservice")
public class SensorService {


	private SensorDAO sdao = new SensorDAO();

	@Path("/createsensor/{database}+{type}")
	@POST
	@Produces("application/json")
	public Response createSensor(@PathParam("database") String dbUrlBit, @PathParam("type") String type) {
		boolean bool = false;
		String fail = "failure";

		bool = sdao.CreateSensor(dbUrlBit, type);

		if (bool && !dbUrlBit.isEmpty() && !type.isEmpty()) {
			String check = "sensor created";
			System.out.println(check);
			return Response.status(201).entity(check).build();
		}

		System.out.println(fail);
		return Response.status(409).entity(fail).build();
	}

	@Path("/deletesensor/{database}+{idsensor}")
	@DELETE
	@Produces("application/json")
	public Response deleteSensor(@PathParam("database") String dbUrlBit, @PathParam("idsensor") int idsensor) {
		boolean bool = false;
		String fail = "sensor not deleted";

		bool = sdao.deleteSensor(idsensor, dbUrlBit);

		if (bool) {
			String check = "sensor deleted";
			return Response.status(200).entity(check).build();
		}

		return Response.status(404).entity(fail).build();
	}

	@Path("/updateSensor/{database}+{idsensor}+{type}")
	@PUT
	@Produces("application/json")
	public Response updateSensor(@PathParam("database") String dbUrlBit, @PathParam("idsensor") int idsensor,
			@PathParam("type") String newtype) {
		boolean bool = false;
		String fail = "sensor not updated";

		bool = sdao.updateSensor(idsensor, newtype, dbUrlBit);

		if (bool) {
			String check = "sensor updated";
			return Response.status(200).entity(check).build();
		}

		return Response.status(404).entity(fail).build();
	}

	@Path("/getsensorlist/{database}")
	@GET
	@Produces("application/json")
	public Response getSensorList(@PathParam("database") String dbUrlBit) {

		List<Sensor> list = new ArrayList<Sensor>();
		list = sdao.getAllSensors(dbUrlBit);

		String json = new Gson().toJson(list);
		return Response.status(200).entity(json).build();
	}
	
	@Path("/addSensorToHouse/{database}+{idsensor}+{idhuis}")
	@POST
	@Produces("application/json")
	public Response addSensorToHouse(@PathParam("database") String dbUrlBit, @PathParam("idsensor") int idsensor, @PathParam("idhuis") int idhuis) {
		String json = "fail" ;
		boolean bool = false;
		
		bool = sdao.addSensorToHouse(dbUrlBit, idsensor, idhuis);
		
		if(bool) {
			json = "succes";
			return Response.status(200).entity(json).build();
		}
		
		return Response.status(404).entity(json).build();
	}

}
