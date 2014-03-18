<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>		
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	<link href="//datatables.net/download/build/nightly/jquery.dataTables.css" rel="stylesheet" type="text/css" />

		<script	type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script type="text/javascript"  src="//cdn.datatables.net/1.10.0-beta.2/js/jquery.dataTables.js"></script>

         <!-- page script -->
       <script type="text/javascript">
       
        $( document ).ready(function()  {


            // Setup - add a text input to each footer cell
            $('#example tfoot th').each( function () {
                var title = $('#example thead th').eq( $(this).index() ).text();
                $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
            } );
         
            var table =  $('#example').DataTable( {
                                  
                    "bProcessing": true,
                    "bServerSide": true,
                    sAjaxSource: '${request.contextPath + '/person/listAjax'}' ,
                    "aoColumns": [
                          	    {mDataProp: 'firstName', sClass: 'center', bSortable: true, bSearchable:true},
                          	    {mDataProp: 'lastName', bSortable: true, bSearchable:true},
                          	    {mDataProp: 'dateOfBirth', bSortable: true, bSearchable:false},
                          	  ],
                    sPaginationType: "full_numbers",
                    aLengthMenu: [[5,10,100, 500, 1000, 5000, -1], [5,10,100, 500, 1000, 5000, "All"]],
                    iDisplayLength: 5
                } );

         // Apply the filter
            $("#example tfoot input").on( 'keyup change', function () {
                table
                    .column( $(this).parent().index()+':visible' )
                    .search( this.value )
                    .draw();
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
					
				</tbody>
			</table>
			
		
	</body>
</html>
