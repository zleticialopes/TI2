package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import dao.AnimalDAO;
import model.Animal;
import spark.Request;
import spark.Response;


public class ProdutoService {

	private AnimalDAO animalDAO = new AnimalDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public ProdutoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Animal(), FORM_ORDERBY_DESCRICAO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Animal(), orderBy);
	}

	
	public void makeForm(int tipo, Animal animal, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umAnimal = "";
		if(tipo != FORM_INSERT) {
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/produto/list/1\">Novo Produto</a></b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/animal/";
			String name, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Animal";
				descricao = "cachorro, gato, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + animal.getCodigo();
				name = "Atualizar Produto (ID " + animal.getCodigo() + ")";
				descricao = animal.getNome();
				buttonLabel = "Atualizar";
			}
			umAnimal += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Descrição: <input class=\"input--register\" type=\"text\" name=\"descricao\" value=\""+ descricao +"\"></td>";
			umAnimal += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ animal.getPreco() +"\"></td>";
			umAnimal += "\t\t\t<td>Quantidade: <input class=\"input--register\" type=\"text\" name=\"quantidade\" value=\""+ animal.getQuantidade() +"\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Data de fabricação: <input class=\"input--register\" type=\"text\" name=\"dataFabricacao\" value=\""+ animal.getDataFabricacao().toString() + "\"></td>";
			umAnimal += "\t\t\t<td>Data de validade: <input class=\"input--register\" type=\"text\" name=\"dataValidade\" value=\""+ animal.getDataValidade().toString() + "\"></td>";
			umAnimal += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Produto (ID " + animal.getID() + ")</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Descrição: "+ animal.getDescricao() +"</td>";
			umAnimal += "\t\t\t<td>Preco: "+ animal.getPreco() +"</td>";
			umAnimal += "\t\t\t<td>Quantidade: "+ animal.getQuantidade() +"</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Data de fabricação: "+ animal.getDataFabricacao().toString() + "</td>";
			umAnimal += "\t\t\t<td>Data de validade: "+ animal.getDataValidade().toString() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ANIMALO>", umAnimal);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Produtos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DESCRICAO + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Animal> animais;
		if (orderBy == FORM_ORDERBY_ID) {                 	animais = animalDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_DESCRICAO) {		animais = animalDAO.getOrderByDescricao();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			animais = animalDAO.getOrderByPreco();
		} else {											animais = animalDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Animal p : animais) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getDescricao() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getID() + "', '" + p.getDescricao() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));
		
		String resp = "";
		
		Animal animal = new Animal(-1, descricao, preco, quantidade, dataFabricacao, dataValidade);
		
		if(animalDAO.insert(animal) == true) {
            resp = "Animal (" + descricao + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Animal (" + descricao + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Animal animal = (Animal) animalDAO.get(id);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, animal, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Animal animal = (Animal) animalDAO.get(id);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, animal, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Animal animal = animalDAO.get(id);
        String resp = "";       

        if (animal != null) {
        	animal.setDescricao(request.queryParams("descricao"));
        	animal.setPreco(Float.parseFloat(request.queryParams("preco")));
        	animal.setQuantidade(Integer.parseInt(request.queryParams("quantidade")));
        	animal.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	animal.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));
        	animalDAO.update(animal);
        	response.status(200); // success
            resp = "Animal (ID " + animal.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Produto (ID \" + animal.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Animal animal = animalDAO.get(id);
        String resp = "";       

        if (animal != null) {
        	animalDAO.delete(id);
            response.status(200); // success
            resp = "Animal (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Animal (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}