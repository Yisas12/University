#include <iostream>
#include <stdio.h>
#include <fstream>
using namespace std;

const int MAX = 100000;

int posicion(int v[], int ini, int fin)
{
	int p = 0;
	int mediana = (ini + fin) / 2;

	if (ini >= fin) return fin;
	
	if (v[mediana] == v[mediana - 1]) {
		if ((mediana - 1 - ini) % 2 == 1) {
			p = posicion(v, ini, mediana);
		}
		else
			p = posicion(v, mediana + 1, fin);
	}
	else if (v[mediana] == v[mediana + 1]) {
		if ((fin - mediana + 1) % 2 == 1) {
			p = posicion(v, mediana, fin);
		}
		else
			p = posicion(v, ini, mediana - 1);
	}
	else p = mediana;

return p;

}
/*Coste del algoritmo:
a es el número de llamadas recursivas, que es igual a 1
b es el factor de disminución del tamaño de los datos, que es igual a 2 ya que divides el array por la mitad
c * n^k es el coste de preparación de las llamadas, que es 0 ya que las operaciones de preparación son constantes, por lo que el coste del algoritmo es O(logn)
*/
void resuelve() {
	int elementos[MAX];
	int numElems;
	//lectura de los datos, numElems>=1
	cin >> numElems;

	for (int i = 0; i < numElems; i++) { cin >> elementos[i]; }

	//LLAMA aqui a tu funcion recursiva
	int pos = posicion(elementos, 0, numElems - 1);
	cout << pos << endl;
	
}



int main() {
	// ajuste para que cin extraiga directamente de un fichero
/*#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif*/

	unsigned int numCasos;
	cin >> numCasos;
	// Resolvemos
	while (numCasos--) {
		resuelve();
	}
	//system("PAUSE");


	// restablecimiento de cin

/*#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif*/

	return 0;
}