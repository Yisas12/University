#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

bool resuelveCaso() {
	int D, n, v[200000], suma = 0;
	bool apta = true;

	//Leer caso de prueba
	cin >> D >> n;
	if (!std::cin)
		return false;

	//Resolver problema
	for (int i = 0; i < n; ++i) {
		cin >> v[i];
	}

	for (int i = 1; i < n; ++i) {
		if (v[i] > v[i - 1]) {
			suma += v[i] - v[i - 1];
		}
		else
			suma = 0;

		if (suma > D) {
			apta = false;
			i = n;//para que se salga de bucle
		}
	}
	//Escribir resultado
	if (apta)
		cout << "APTA\n";
	else
		cout << "NO APTA\n";
	return true;
}


int main() {
	// ajuste para que cin extraiga directamente de un fichero
	//#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
	//#endif

	while (resuelveCaso());

	// restablecimiento de cin
	//#ifndef DOMJUDGE
	//std::cin.rdbuf(cinbuf);
	//system("pause");
	//#endif
	return 0;
}