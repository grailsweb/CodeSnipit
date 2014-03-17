<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>		
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		

<link href="http://code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">

<script type="text/javascript" language="javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.min.js "></script>

<script type="text/javascript" language="javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>

<script type="text/javascript" language="javascript" src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
		
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
				</tbody>
			</table>
			
		
         <!-- page script -->
        <script type="text/javascript">
        var oTable;
        $(document).ready(function(){
            $('#example').dataTable({
            		"dom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',    			
              	  bRetrieve: true,
              	  bDestroy: true,               	 
              	  bScrollCollapse: true,
              	  sServerMethod: 'POST',
              	  bProcessing: true,
              	  bServerSide: true,
              	  sAjaxSource: '${request.contextPath + '/person/listAjax'}' ,
              	  sPaginationType: "full_numbers",
              	  aoColumns: [
              	    {mDataProp: 'firstName', sClass: 'center', bSortable: true, bSearchable:true},
              	    {mDataProp: 'lastName', bSortable: true, bSearchable:true},
              	    {mDataProp: 'dateOfBirth', bSortable: true, bSearchable:false},
              	  ],
              	  aLengthMenu: [[5,10,100, 500, 1000, 5000, -1], [5,10,100, 500, 1000, 5000, "All"]],
                    iDisplayLength: 5}).columnFilter();
       		 
       });
          
       </script>
	</body>
</html>
