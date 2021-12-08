#include "Differentiator.h"
#include "Function.h"
#include <math.h>
#include <stdio.h>
#include<float.h>



int main(int argc, char *argv[])
{
	double dy,df, eps=1.E-5, x=1.5;

	// wrap the C sine function into our C++ Warper class
	Function f = Function(sin);
	// cosine is the true derivative of sine
	df = cos(x);
	// lets see what our implementation does
	dy = differentiate (f, x, eps);
	printf("x       expected= cos(x)     	dsin(x)                             abs(dy-df)          	abs(dy-df)<eps? \n");
	printf("%.2f        %6.2f               %6.2f              %6.30f		%d", x, df, dy, abs(dy-df), abs(dy-df)<eps);


	//double h= 0.0000099 - pow((DBL_EPSILON/1.0), (1.0/3.0));
	double h= 0.0000099 - pow((3.0 * DBL_EPSILON), (1.0/3.0));


	printf("\n EPlsion von Computer ist: %6.30f", h);

	// compare the difference within the C assert
	assert(abs(dy-df) < eps);



}
