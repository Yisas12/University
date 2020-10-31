#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int aprende(vector<int> v, int &ini, int &fin) {
	int cont = 0, max = 0;

	for (int i = 0; i < v.size(); ++i) {
		if (v.at(i) == 0)
			cont = 0;

		else {
			cont++;;
		}
		if (cont > max) {
			max = cont;
			ini = i - cont + 1;
			fin = i;
		}
	}
	return max;
}

void resuelveCaso() {
	int n, elem, tamano = 0, ini = 0, fin = 0;
	vector<int> v;

	cin >> n;
	for (int i = 0; i < n; ++i) {
		cin >> elem;
		v.push_back(elem);
	}

	tamano = aprende(v, ini, fin);
	if (tamano > 0)
		cout << tamano << " -> [" << ini << "," << fin << "]" << endl;
	else
		cout << "SIGUE BUSCANDO\n";
}

int main() {
	// Para la entrada por fichero.
	//#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
	//#endif


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

	//system("pause");
	return 0;
}