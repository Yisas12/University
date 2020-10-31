//Jesús Cencerrado Pedrero


#include <iostream>
#include <fstream>
using namespace std;



/*void resuelveCaso(int casos) {
	//resuelve aqui tu caso
	//Lee los datos
	//Calcula el resultado
	//Escribe el resultado

}*/

int main() {
	// Para la entrada por fichero.
/*#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif*/


	int numCasos;
	cin >> numCasos;
	// Resolvemos
	if (numCasos >= 0 && numCasos <= 5)
	{
		for (int i = 0; i < numCasos; i++) {
			cout << "Hola mundo." << endl;
		}
	}
	
	//system("pause");
/*#ifndef DOMJUDGE // para dejar todo como estaba al principio
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif*/

	return 0;
}
