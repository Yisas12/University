//Jesús Cencerrado Pedrero


#include <iostream>
#include <fstream>
#include <string>
using namespace std;



void resuelveCaso() {
	//resuelve aqui tu caso
	//Lee los datos
	//Calcula el resultado
	//Escribe el resultado
}

int main() {
	// Para la entrada por fichero.
/*#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif*/

	char nombre[100];
	string basura;
	unsigned int numCasos;
	std::cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		cin >> basura >> nombre;
		cout << "Hola, " << nombre << "." << endl;
	}


/*#ifndef DOMJUDGE // para dejar todo como estaba al principio
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif*/

	return 0;
}