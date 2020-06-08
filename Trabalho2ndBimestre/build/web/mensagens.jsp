<%-- 
    Document   : mensagens
    Created on : 28/05/2019, 15:18:59
    Author     : aluno
--%>

<%@page import="model.Amigo"%>
<%@page import="java.util.List"%>
<%@page import="controller.DAOJPA"%>
<%@page import="model.Usuario"%>
<%@page import="model.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/myCss.css">
        <title>NewsFeed</title>
    </head>
    <body class="b1">
        <div class="reg">
            <form action="mensagens.jsp" method="post" align="center">
                Tipo: <select name="sbTypes">
                    <option>texto</option>
                    <option>vídeo</option>
                    <option>foto</option>
                </select>
                <br/>
                <textarea name="txtMensagem" rows="8" cols="120" value="Insira sua Mensagem aqui.">
                </textarea>
                <br/>
                <input type="submit" value="Enviar" name="btnEnviar" />
                <br/>
            </form>
            <%
                Mensagem objM;
                Usuario objU;
                DAOJPA dao;
                String mens = "";
                try {
                    mens = request.getParameter("txtMensagem");
                    if (mens.length() > 0) {
                        objU = (Usuario) session.getAttribute("usuario");
                        dao = new DAOJPA();
                        objM = new Mensagem();
                        objM.setCodigoremetente(objU);
                        objM.setTipo(request.getParameter("sbTypes"));
                        objM.setDados(request.getParameter("txtMensagem"));
                        dao.gravar(objM);
                    }
                    response.sendRedirect("mensagens.jsp");
                } catch (Exception ex) {
                   /*if (!ex.getMessage().equalsIgnoreCase("null")) {
                        out.println("<h1> Erro ao enviar mensagem: " + ex.getMessage() + "</h1>");
                   }*/
                }
            %>
        </div>

        <div class="reg">
            <%
                DAOJPA crud;
                List<Object> Msgs = null;
                try {
                    objM = new Mensagem();
                    objU = new Usuario();
                    crud = new DAOJPA();
                    objU = (Usuario) session.getAttribute("usuario");
                    Msgs = crud.mensagens(objU.getCodigo());
                    if (Msgs != null) {
                        for (int i = 0; i < Msgs.size(); i++) {
                            objM = (Mensagem) Msgs.get(i);
                            if (objM.getTipo().equalsIgnoreCase("texto")) {
            %>
            <form align="left">
                <h2> <%=objM.getCodigoremetente().getNome()%>: </h2>
                <h4> Postado em: <%=objM.getDatahora()%> </h4>
                <br/>
                <p align="center"> <%=objM.getDados()%> </p>
                <br/>
            </form>
            <%
                }
                if (objM.getTipo().equalsIgnoreCase("foto")) {
            %>
            <form>                  
                <h2> <%=objM.getCodigoremetente().getNome()%>: </h2>
                <h4> Postado em: <%=objM.getDatahora()%> </h4>
                <br/>
                <div align="center"><img src="<%=objM.getDados()%>" width="560" height="315" align="center"/></div>
                <br/>
            </form>
            <%
                }
                if (objM.getTipo().equalsIgnoreCase("vídeo")) {
            %>
            <form>                    
                <h2> <%=objM.getCodigoremetente().getNome()%>: </h2>
                <h4> Postado em: <%=objM.getDatahora()%> </h4>
                <br/>
                <div align="center"><embed width="560" height="315" src="<%=objM.getDados()%>" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen ></embed></div>
                <br/>
            </form>
            <%
                            }
                        }
                    } else
                        out.println("<h1>SEM MENSAGENS PARA EXIBIR NO MOMENTO</h1>");
                } catch (Exception ex) {
                    if (!ex.getMessage().equalsIgnoreCase("null")) {
                        out.println("<h1> Erro ao exibir mensagens: " + ex.getMessage() + "</h1>");
                    }
                }
            %>
        </div>

        <div class="reg">
            <%
                List<Usuario> listaU = null;
                List<Usuario> listaA = null;
                try {
                    objU = new Usuario();
                    dao = new DAOJPA();
                    objU = (Usuario) session.getAttribute("usuario");
                    listaA = dao.listarAmigos(objU.getCodigo());
                    if (listaA == null) {
            %>
            <h1> Você ainda não possui amigos </h1>
            <%
                }
                if (listaA != null) {
            %>
            <h1>Meus amigos:</h1>
            <%
                for (int i = 0; i < listaA.size(); i++) {
                    objU = (Usuario) listaA.get(i);
            %>
            <form action="mensagens.jsp" action="Post">
                <input type="text" name="txtCodRa" hidden value="<%=objU.getCodigo()%>"/>
                <h4> Nome: <%=objU.getNome()%> </h4>
                <input type="submit" value="Remover" name="br1" class="button" />
            </form>
            <%
                    }
                }
                Usuario objA;
                Amigo objAm;
                int codigoOldamigo;
                try {
                    codigoOldamigo = Integer.parseInt(request.getParameter("txtCodRa"));
                    objAm = new Amigo();
                    crud = new DAOJPA();
                    objU = (Usuario) session.getAttribute("usuario");
                    objA = (Usuario) crud.getById(Usuario.class, codigoOldamigo);
                    objAm = (Amigo) crud.getAmIds(Amigo.class, objU.getCodigo(), objA.getCodigo());
                    crud.remover(Amigo.class, objAm.getCodigo());
            %> <h3> Voce Parou de seguir <%=objA.getNome()%> </h3> <%
                        response.sendRedirect("mensagens.jsp");
                    } catch (Exception ex) {
                        if(!ex.getMessage().equalsIgnoreCase("null")){
                        out.println("<h1> Erro ao excluir amigo: " + ex.getMessage() + "</h1>");
                        }       
                    }
                } catch (Exception ex) {
                    if (!ex.getMessage().equalsIgnoreCase("null")) {
                        out.println("<h1> Erro ao listar usuarios: " + ex.getMessage() + "</h1>");
                    }
                }
            %>
        </div>

        <div class="reg"> 
            <%
                dao = new DAOJPA();
                objU = (Usuario) session.getAttribute("usuario");
                listaU = dao.listarNotAmigos(objU.getCodigo());
                if (listaU == null) {
            %>
            <h1> Parabens, você é amigo de toda a base </h1>
            <%
                }
                if (listaU != null) {
            %>
            <h1> Amigos que talvez você conheça: </h1>
            <%
                for (int y = 0; y < listaU.size(); y++) {
                    objU = (Usuario) listaU.get(y);
            %>
            <form action="mensagens.jsp">
                <h3> Nome: <%=objU.getNome()%> </h3>
                <input type="text" name="txtCodNa" hidden value="<%=objU.getCodigo()%>"/>
                <div align="right"><input type="submit" value="Seguir" name="bs1" class="button" float="right" /></div>
            </form>                 
            <%
                    }
                }
                int codigoNewamigo;
                Amigo objAm;
                Usuario objA;
                try {
                    codigoNewamigo = Integer.parseInt(request.getParameter("txtCodNa"));
                    objAm = new Amigo();
                    crud = new DAOJPA();
                    objA = (Usuario) crud.getById(Usuario.class, codigoNewamigo);
                    objA.getNome();
                    objAm.setCodusuario((Usuario) session.getAttribute("usuario"));
                    objAm.setCodamigo(objA);
                    crud.gravar(objAm);
            %>
            <h1> Voce seguiu <%=objA.getNome()%> </h1>
            <%
                    response.sendRedirect("mensagens.jsp");
                } catch (Exception ex) {
                    if (!ex.getMessage().equalsIgnoreCase("null")) {
                        out.println("<h1> Erro ao seguir: " + ex.getMessage() + "</h1>");
                    }
                }
            %>
        </div>
    </body>
</html>
