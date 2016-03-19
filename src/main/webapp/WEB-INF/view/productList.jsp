
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/view/template/header.jsp" %>>


<br><br>
<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container-wrapper">

   <div class="container">
        <div class="page-header">
                <h1> All products</h1>

                <p class="lead">Check out all the awesome product</p>
                </div>

<table class="table table-striped table-hover">
<thead>
<tr class="bg-success">
     
        <th>Product Name</th>
        <th>Category</th>
        <th>Condition</th>
        <th>Price</th>
        <th></th>
        
        </tr>
</thead>
 <c:forEach items="${products}" var="product">
<tr>
       
        <td>${product.productName}</td>
        <td>${product.productCategory}</td>
        <td>${product.productCondition}</td>
        <td>${product.productPrice}USD</td>
        <td class="active"><a href="<c:url value="/productlist/viewProduct/${product.productId}"/>">Info</a></td>
              
</tr>
</c:forEach>
</table>

<%@include file="/WEB-INF/view/template/footer.jsp" %>