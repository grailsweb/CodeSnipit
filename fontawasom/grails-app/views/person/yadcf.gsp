<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>		
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="http://yadcf-showcase.appspot.com/resources/css/jquery.dataTables.yadcf.css" rel="stylesheet" type="text/css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="http://yadcf-showcase.appspot.com/resources/js/fnReloadAjax.js"></script>
 <script type="text/javascript" language="javascript" src="http://yadcf-showcase.appspot.com/resources/js/jquery.dataTables.yadcf.js"></script>

		
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
              	    {mDataProp: 'dateOfBirth', bSortable: true, bSearchable:true},
              	  ],
              	  aLengthMenu: [[5,10,100, 500, 1000, 5000, -1], [5,10,100, 500, 1000, 5000, "All"]],
                    iDisplayLength: 5}).yadcf([{
    	        column_number: 0
    	    }, {
    	        column_number: 1,
    	        filter_type: "auto_complete"
    	    },{
    	        column_number: 2,
    	        filter_type: "auto_complete"
    	    }]);
       		 
       });
          
       </script>
	</body>
</html>
