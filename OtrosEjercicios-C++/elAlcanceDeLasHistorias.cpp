#include<iostream>
#include <vector>
using namespace std;

int p(long long int v[], int cant)
{
	int max1 = v[0] - v[1];
	int posM = 0;
	//Calcula el resultado
	for (int j = 1; j < cant; j++)
	{
		if (v[posM] - v[j] > max1) max1 = v[posM] - v[j];
		if (v[posM] < v[j]) posM = j;
	}
	return max1;
}

void resuelveCaso() {
		//resuelve aqui tu caso
	long long int v[300000], cantidad;
		//Lee los datos
	cin >> cantidad;
	
		for (int i = 0; i < cantidad; i++)
		{
			cin >> v[i];
		}

		
		//Escribe el resultado
		cout << p(v,cantidad) << endl;
}

int main() {
		// Para la entrada por fichero.
/*#ifndef DOMJUDGE
		std::ifstream in("casos.txt");
		auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif*/


	unsigned int numCasos;
	cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		resuelveCaso();
	}


//#ifndef DOMJUDGE // para dejar todo como estaba al principio
		//std::cin.rdbuf(cinbuf);
		//system("PAUSE");
//#endif

		return 0;
	}
