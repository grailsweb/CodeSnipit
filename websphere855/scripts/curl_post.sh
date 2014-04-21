#!/bin/bash
for i in add  sub multi div
do
	curl -i -X POST  --data "num1=70&num2=50&group1=${i}" http://localhost/example/ejb3/WebClient.jsp
done
