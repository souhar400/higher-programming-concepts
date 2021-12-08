#include <stdio.h>
#include <math.h>
#include<float.h>
#include "Differentiator.h"

double differentiate(Function &f, double x, double eps)
{
	/*
	double h = 0.00001;
	return (f(x+h)-f(x-h))/(2*h);
	 */


	int n;
	double actualAblt, lastAblt;

	//double h = 0.0000099;
	//double h = cbrt(DBL_EPSILON*2.0);
	//double h = cbrt(DBL_EPSILON*2.0);
	//double h = 0.000001, diff, lastDiff;
	double h= pow(DBL_EPSILON*2.0, (1.0/3.0));

	lastAblt = (f(x+h)-f(x-h))/(2.0*h);


	while(true) {
		h = h/2.0;
		actualAblt = (f(x+h)-f(x-h))/(2.0*h);
		if(abs(actualAblt-lastAblt)<eps)
			{
				return actualAblt;
			}
		else lastAblt = actualAblt;
	}
}
