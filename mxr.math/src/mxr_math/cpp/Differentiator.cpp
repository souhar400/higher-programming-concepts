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
	double diff, lastDiff;
	//double h = 0.0000099;
	//double h = cbrt(DBL_EPSILON*2.0);
	//double h = cbrt(DBL_EPSILON*2.0);

	//double h = 0.000001, diff, lastDiff;
	double h= pow(DBL_EPSILON*2.0, (1.0/3.0));

	lastDiff = (f(x+h)-f(x-h))/(2.0*h);


	while(true) {
		h = h/2.0;
		diff = (f(x+h)-f(x-h))/(2.0*h);
		if(abs(diff-lastDiff)<eps)
			{
				return diff;
			}
		else lastDiff = diff;
	}

/*
		for(n=1;n<1000;n++)
		{
			h=0.0001/(7*n);
			diff = (f(x+h)-f(x-h))/(2.0*h);

			if(abs(diff-lastDiff)<eps)
			{
				return diff;
			}

			lastDiff = diff;
		}
		*/
	printf("f'(%f) < eps Not Found\n",x);

	//return diff;


}
