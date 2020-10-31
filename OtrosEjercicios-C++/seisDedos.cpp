#include <iostream>
#include <fstream>
#include<vector>
using namespace std;

int seisDedos(vector<int> v, int A)
{
	int max = 0, anio, cont = 0;

	for (int i = 0; i < v.size(); i++)
	{

	}
}

bool resuelveCaso() {
	int N, A, nac, sol;
	vector<int> v;
	//Leer caso de prueba
	cin >> N >> A;

	if (N == 0 && A == 0)
		return false;


	//Resolver problema
	for (int i = 0; i < N; i++)
	{
		cin >> nac;
		v.push_back(nac);
	}
	//Escribir resultado
	sol = seisDedos(v, A);
	return true;
}


int main() {

	// ajuste para que cin extraiga directamente de un fichero
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	while (resuelveCaso());

	// restablecimiento de cin
#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("pause");
#endif
	return 0;
}