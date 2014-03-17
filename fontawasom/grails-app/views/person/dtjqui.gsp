
<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="datatablejqui"/>
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		 <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0-beta.2/css/jquery.dataTables.css">
	</head>
	<body>
		 
		    <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
               <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        DataTables                      
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Person</a></li>
                        <li class="active">JSON P</li>
                    </ol>
                </section>          

                <!-- Main content -->
                <section class="content ">
                
                
		<div id="list-person" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table id="example" class="display" cellspacing="0" width="100%">
			<thead>
					<tr>
					<td>	${message(code: 'person.firstName.label', default: 'First Name')} </td>					
					<td>${message(code: 'person.lastName.label', default: 'Last Name')}</td>					
						<td>${message(code: 'person.dateOfBirth.label', default: 'Date Of Birth')}</td>					
					</tr>
				</thead>
				<tbody>
				<g:each in="${personInstanceList}" status="i" var="personInstance">
					<tr>
					
						<td> ${fieldValue(bean: personInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: personInstance, field: "lastName")}</td>
					
						<td><g:formatDate date="${personInstance.dateOfBirth}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
		</div>
		
		
		 
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        
         <!-- page script -->
        <script type="text/javascript">
        $( document ).ready(function()  {
        
                $('#example').dataTable({
                	  "dom": 'T<"clear">lfrtip',
                      "tableTools": {
                          "sSwfPath": "http://next.datatables.net/release-datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                      },
                	  bJQueryUI: true,
                	  bRetrieve: true,
                	  bDestroy: true,
                	  bServerSide: true,
                	  sScrollY: '345px',
                	  bScrollCollapse: true,
                	  sServerMethod: 'POST',
                	  bProcessing: true,
                	  sAjaxSource: '${request.contextPath + '/person/listAjax'}' ,
                	  sPaginationType: "full_numbers",
                	  aoColumns: [
                	    {mDataProp: 'firstName', sClass: 'center', bSortable: true, bSearchable:true},
                	    {mDataProp: 'lastName', bSortable: true, bSearchable:true},
                	    {mDataProp: 'dateOfBirth', bSortable: true, bSearchable:true},
                	  ],
                	  aLengthMenu: [[5,10,100, 500, 1000, 5000, -1], [5,10,100, 500, 1000, 5000, "All"]],
                      iDisplayLength: 5
                	});
            });
        </script>
	</body>
</html>
