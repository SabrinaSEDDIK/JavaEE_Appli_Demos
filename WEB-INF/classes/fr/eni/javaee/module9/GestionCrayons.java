package fr.eni.javaee.module9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.eni.javaee.module9.bo.Crayon;


@Path("/crayons")
public class GestionCrayons {

	//Attention aux accès concurrents!
	private static List<Crayon> listeCrayons = new ArrayList<>();
	static
	{
		listeCrayons.add(new Crayon(1, "bille", "bleu"));
		listeCrayons.add(new Crayon(2, "plume", "vert"));
	}
	
	@GET
	public List<Crayon> getCrayons()
	{
		return listeCrayons;
	}
	
	@GET
	@Path("/{id : \\d+}")
	public Crayon getCrayon(@PathParam("id") int id)
	{
		System.out.println(id);
		Crayon crayonARetourner=null;
		for (Crayon crayon : listeCrayons) {
			if(crayon.getId()==id)
			{
				crayonARetourner=crayon;
			}
		}
		return crayonARetourner;
	}
	
	@POST
	public Crayon ajouterCrayon(@FormParam("type") String type, @FormParam("couleur") String couleur)
	{
		Crayon crayon = new Crayon(new Random().nextInt(100),type, couleur);
		listeCrayons.add(crayon);
		return crayon;
	}
	
	@PUT
	@Path("/{id:\\d+}")
	public Crayon modifierCrayon(@PathParam("id") int id, @FormParam("type") String type, @FormParam("couleur") String couleur)
	{
		Crayon crayonAModifier=null;
		for (Crayon crayon : listeCrayons) {
			if(crayon.getId()==id)
			{
				crayon.setType(type);
				crayon.setCouleur(couleur);
				crayonAModifier=crayon;
			}
		}
		return crayonAModifier;
	}
	
	@DELETE
	@Path("/{id:\\d+}")
	public boolean supprimerCrayon(@PathParam("id") int id)
	{
		Crayon crayonASupprimer=null;
		for (Crayon crayon : listeCrayons) {
			if(crayon.getId()==id)
			{
				crayonASupprimer=crayon;
			}
		}
		return listeCrayons.remove(crayonASupprimer);
	}			
}