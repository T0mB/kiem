package main.java.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import main.java.dao.*;
import main.java.domain.*;

@Path("/huisservice")
public class HuisService {

	private HuisDAO hdao = new HuisDAO();

	@Path("/getlist+{db}")
	@GET
	@Produces("application/json")
	public Response getList(@PathParam("db") String db) throws JSONException, IOException, ClassNotFoundException {


		List<Huis> list = new ArrayList<Huis>();
		list = hdao.selectHuis(db, "select * from huis");


		String json = new Gson().toJson(list);
		return Response.status(200).entity(json).build();

	}

}
