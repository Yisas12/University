//Jesús Cencerrado Pedrero


#include <iostream>
#include <fstream>
#include <vector>
using namespace std;



void resuelveCaso() {
	//resuelve aqui tu caso
	int numComp;
	int num;
	vector<int> a;

	//Lee los datos
		cin >> numComp;

		for (int j = 0; j < numComp; j++)
		{
			cin >> num;
			a.push_back(num);
		}
		

		int picos = 0;
		int valles = 0;

		//Calcula el resultado
		for (int k = 0; k < numComp - 2; k++)
		{

			if (a[k + 1] < a[k] && a[k + 1] < a[k + 2])
			{
				picos++;
			}
			else if (a[k + 1] > a[k] && a[k + 1] > a[k + 2])
			{
				valles++;
			}
		}

		//Escribe el resultado
		cout << valles << " " << picos << endl;
}


int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif


	int numCasos;
	std::cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		resuelveCaso();
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	std::cin.rdbuf(cinbuf);
	//system("PAUSE");
#endif

	return 0;
}
