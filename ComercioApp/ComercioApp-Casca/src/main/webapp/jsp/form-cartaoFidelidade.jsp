<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <form action="/cartaoFidelidade" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="id" name="id" value="${obj.id}">
        <h2>Cartão Fidelidade</h2>
        <div class="row">
            <div class="form-group col-xs-6">                                  
                <label for="cliente" class="control-label col-xs-6">Cliente:</label>
                <select name="cliente" class="form-control">
                    <c:forEach var="cliente" items="${listCliente}">
                        <option value="${cliente.id}" ${cliente.id == obj.cliente.id?"selected":""}>${cliente}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-xs-2">
                <label for="senha" class="control-label col-xs-2">Senha:</label>
                <input type="password" name="senha" id="senha" class="form-control" value="${obj.senha}" required="true"/>
            </div>      
        </div>
        <div class="row">
            <div class="form-group col-xs-2">
                <label for="vencimento" class="control-label">Dia de Vencimento:</label>
                <input type="number" min="1" max="31" name="vencimento" id="vencimento" class="form-control" value="${obj.vencimento}" required="true"/>
            </div>
            <div class="form-group col-xs-2">
                <label for="limite" class="control-label">Limite:</label>
                <input type="number" min="0" max="999999" name="limite" id="limite" class="form-control" value="${obj.limite}" required="true"/>
            </div>
            <div class="form-group col-xs-2">
                <label for="qtdPontos" class="control-label">Pontos:</label>
                <input type="number" min="0" max="999999" name="qtdPontos" id="qtdPontos" class="form-control" value="${obj.qtdPontos}" required="true"/>
            </div>
            <div class="form-group col-xs-2">
                <label for="fatorConversao" class="control-label">Fator Conversão:</label>
                <input type="number" min="0" max="100" name="fatorConversao" id="fatorConversao" class="form-control" value="${obj.fatorConversao}" required="true"/>
            </div>  
        </div>
        <div class="row">
            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
        </div>
    </form>
</div>
<%@include file="../footer.jspf" %>