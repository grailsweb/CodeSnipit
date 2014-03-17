<%@ page import="fontawasom.Person" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="datatable-filter"/>
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		 
		    <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
               <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Data Fileter                      
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Person</a></li>
                        <li class="active">Filter</li>
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
					<th>	${message(code: 'person.firstName.label', default: 'First Name')} </th>					
					<th>${message(code: 'person.lastName.label', default: 'Last Name')}</th>					
					<th>${message(code: 'person.dateOfBirth.label', default: 'Date Of Birth')}</th>						
					</tr>
				</thead>
				<tfoot>
					<tr>
					<th>	${message(code: 'person.firstName.label', default: 'First Name')} </th>					
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
			
		</div>
		
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        
         <!-- page script -->
        <script type="text/javascript">
        var oTable;
        $( document ).ready(function()  {
        	'use strict';

                    
        	oTable  = $('#example').dataTable({
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
                  iDisplayLength: 5
            	}).yadcf([{
        	        column_number: 0
        	    }, {
        	        column_number: 1,
        	        filter_type: "auto_complete"
        	    },{
        	        column_number: 2,
        	        filter_type: "auto_complete"
        	    }]);
    	
     	    return;
     	
        	var table = $('#example').dataTable({
        			"dom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
                      "tableTools": {
                          "sSwfPath": "http://next.datatables.net/release-datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                      },
                	  bRetrieve: true,
                	  bDestroy: true,                	  
                	  sScrollY: '345px',
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
                      iDisplayLength: 5
                	}).yadcf([{
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
