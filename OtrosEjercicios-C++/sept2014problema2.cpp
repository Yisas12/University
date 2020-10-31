#include <iostream>
#include <cmath>
using namespace std;

//Precondición: -1 < x < 1 ^ n >= 0
//Postcondición: 

double serie(double x, int n) {
	double suma = x;
	double y = x * x;
	double aux = x;
	
	for (double i = 0; i < n; i++)
	{
		cout << suma << endl;

		aux = aux * ((y * (2 * i + 1) / (2 * i + 3)));
		suma += aux;
		
	}
	return suma;
}

int main() {
	double x = -0.5;
	int n = 6;
	double p;

	p = serie(x, n);

	cout << p << endl;

	system("pause");
	return 0;
}