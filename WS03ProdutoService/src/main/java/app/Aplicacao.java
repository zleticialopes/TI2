package app;

import static spark.Spark.*;
import service.ProdutoService;


public class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/animal/insert", (request, response) -> produtoService.insert(request, response));

        get("/animal/:id", (request, response) -> produtoService.get(request, response));
        
        get("/animal/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/animal/update/:id", (request, response) -> produtoService.getToUpdate(request, response));
        
        post("/animal/update/:id", (request, response) -> produtoService.update(request, response));
           
        get("/animal/delete/:id", (request, response) -> produtoService.delete(request, response));

             
    }
}