<!DOCTYPE html>
<html>
<head>
 
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"	rel="stylesheet">
<link	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"	rel="stylesheet">
<link href="http://almsaeedstudio.com/AdminLTE/css/AdminLTE.css"	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/505bef35b56/integration/bootstrap/3/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="http://next.datatables.net/release-datatables/extensions/TableTools/css/dataTables.tableTools.css">


<script	src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="http://almsaeedstudio.com/AdminLTE/js/AdminLTE/app.js"		type="text/javascript"></script>
<script	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js" 		type="text/javascript"></script>
<script type="text/javascript"  src="//cdn.datatables.net/1.10.0-beta.2/js/jquery.dataTables.js"></script>
<script type="text/javascript"  src="//cdn.datatables.net/plug-ins/505bef35b56/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="http://next.datatables.net/release-datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
 
 
<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />
</head>
<body>

	<div class="wrapper row-offcanvas row-offcanvas-left">
	<g:render template="/layouts/side"/>
	
		<g:layoutBody />
<r:layoutResources />

	</div>
	<!-- ./wrapper -->

    <!-- page script -->
        <script type="text/javascript">
       
        $( document ).ready(function()  {
            // Setup - add a text input to each footer cell
            $('#example tfoot th').each( function () {
                var title = $('#example thead th').eq( $(this).index() ).text();
                $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
            } );
         
            var table=  $('#example').DataTable( {
                    "dom": 'T<"clear">lfrtip',
                    "tableTools": {
                        "sSwfPath": "http://next.datatables.net/release-datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                    },
                   
                    bProcessing: true,
                    bServerSide: true,
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

</body>
</html>