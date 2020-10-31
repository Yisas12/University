#include <iostream>
#include <vector>
using namespace std;

long long int resolverComplementario(long long int num, long long int &cuenta, long long int &comp, long long int n)
{
	int total = 0, numVueltas = 1, numBueno = 0;
	
	if (num / 10 == 0) {

		for (int i = 0; i < cuenta; i++)
		{
			numVueltas = (numVueltas * 10) + 1;
		}
		numBueno = numVueltas * 9;
		comp = numBueno - n;
	}
	else {
		cuenta++;
		resolverComplementario(num / 10, cuenta, comp, n);
	}

	return comp;
}

long long int inverso(long long int num, long long int cuenta, long long int n, long long int veces, long long int &numero, long long int num2)
{
	long long int nu = 10;

	if (num / 10 == 0)
	{
		cuenta++;
		if (cuenta == 1) {
			if (num2 < 10) numero = n;
			else numero = n * 10;
		}
		else {
			for (int i = 0; i < cuenta; i++)
			{
				numero = numero + ((n % nu * veces));
				n /= 10;
				veces /= 10;
			}
		}
	}
	else
	{
		cuenta++;
		inverso(num / 10, cuenta, n, veces*10, numero, num2);
	}

	return numero;
}

bool resuelveCaso()
{
	long long int numCasos, num, p, n, h;
	long long int cuenta = 0, comp = 0, veces = 1, k = 0, numero = 0;

	cin >> numCasos;

	for (int i = 0; i < numCasos; i++) {
		cin >> num;
		n = num;

		p = resolverComplementario(num, cuenta, comp, n);
		h = inverso(p, k, p, veces, numero, num);
		
		cout << p << " " << h << "\n";
		cuenta = 0;
		veces = 1;
		k = 0;
		numero = 0;
		comp = 0;
		p = 0;
		h = 0;
	}
	

	return false;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}