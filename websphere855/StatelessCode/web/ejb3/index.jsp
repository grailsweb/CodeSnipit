<html>
    <head>
        <title>Calculator</title>
    </head>

    <body bgcolor="pink">
        <h1>Calculator</h1>
        <hr>
        
        <form action="WebClient.jsp" method="POST">
	<p>Enter first value:
            <input type="text" name="num1" size="25" value="70"></p>
            <br>
	    <p>Enter second value:
            <input type="text" name="num2" size="25" value="50"></p>
            <br>
            
			<b>Seclect your choice:</b><br>
  <input type="radio" name="group1" value ="add" checked>Addition<br>
  <input type="radio" name="group1" value ="sub">Subtraction<br>
  <input type="radio" name="group1" value ="multi">Multiplication<br>
  <input type="radio" name="group1" value ="div">Division<br>
  <p>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset"></p>

        </form>


    </body>
</html>
