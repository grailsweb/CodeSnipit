<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>		
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="http://almsaeedstudio.com/AdminLTE/css/AdminLTE.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/505bef35b56/integration/bootstrap/3/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="http://next.datatables.net/release-datatables/extensions/TableTools/css/dataTables.tableTools.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0-beta.2/css/jquery.dataTables.css">
		 
<script	type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script	type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript"  src="//cdn.datatables.net/1.10.0-beta.2/js/jquery.dataTables.js"></script>
<script type="text/javascript"  src="//cdn.datatables.net/plug-ins/505bef35b56/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript"  src="http://next.datatables.net/release-datatables/extensions/TableTools/js/dataTables.tableTools.js"></script> 

<script type="text/javascript"   src="http://almsaeedstudio.com/AdminLTE/js/AdminLTE/app.js"></script>

<script type="text/javascript">
      
        $( document ).ready(function()  {

        	 
            var table=  $('#example').DataTable( {
                    "dom": 'T<"clear">lfrtip',
                    "tableTools": {
                        "sSwfPath": "http://next.datatables.net/release-datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                    },                   
                   "bProcessing": true,
                    "bServerSide": true,
                    "ajax": {
                        "url": "${request.contextPath + '/person/listAjax2'}",
                        "type": "POST"
                    },
                    "columns": [
                        { "data": "firstName","orderable": true , "searchable": true },
                        { "data": "lastName" },
                        { "data": "dateOfBirth" }
                    ],                  
                    "sPaginationType": "full_numbers",
                    "aLengthMenu": [[5,10,100, 500, 1000, 5000, -1], [5,10,100, 500, 1000, 5000, "All"]],
                    "iDisplayLength": 5
                } );

             
            });
        </script>
</head>
<body>

			<table id="example" class="display" cellspacing="0" width="100%">
			<thead>
					<tr>
					<th>${message(code: 'person.firstName.label', default: 'First Name')} </th>					
					<th>${message(code: 'person.lastName.label', default: 'Last Name')}</th>					
					<th>${message(code: 'person.dateOfBirth.label', default: 'Date Of Birth')}</th>						
					</tr>
				</thead>
				<tfoot>
					<tr>
					<th>${message(code: 'person.firstName.label', default: 'First Name')} </th>					
					<th>${message(code: 'person.lastName.label', default: 'Last Name')}</th>					
					<th>${message(code: 'person.dateOfBirth.label', default: 'Date Of Birth')}</th>					
					</tr>
				</tfoot>
				<tbody>
				<g:each in="${personInstanceList}" status="i" var="personInstance">
					<tr>
					
						<td> ${fieldValue(bean: personInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: personInstance, field: "lastName")}</td>
					
						<td><g:formatDate date="${personInstance.dateOfBirth}" /></td>
					
					</tr>
				</g:each>
					<tr>
					
						<td> John</td>
					
						<td>Doe</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Joe</td>
					
						<td>Reed</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Jim</td>
					
						<td>Smith</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Patrick</td>
					
						<td>Hartwin</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Steve</td>
					
						<td>Gunther</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Sam</td>
					
						<td>Whiting</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Sarah</td>
					
						<td>Mathews</td>
					
						<td></td>
					
					</tr>
				
					<tr>
					
						<td> Lisa</td>
					
						<td>Pudock</td>
					
						<td></td>
					
				</tbody>
			</table>
			
		
	</body>
</html>
