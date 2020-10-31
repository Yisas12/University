//Sergio Crespillo Campos, Screspil
//Jesús Cencerrado Pedrero, Yisas

#include <iostream>
#include <vector>
using namespace std;

typedef struct {
	bool menor;
	bool coinc;
	bool cruzado;
	int pos;
}tPack;

tPack funRec(const vector <int>& v, const vector <int>& w, int ini, int fin) {
	tPack pack1, pack2;
	int mitad;

	pack1.coinc = false;
	pack1.menor = false;
	pack1.cruzado = false;

	if (ini == fin) {
		if (v[ini] == w[ini]) {
			pack1.coinc = true;
			pack1.pos = ini;
		}
		else if (v[ini] < w[ini]) {
			pack1.menor = true;
		}
	}
	else {

		mitad = (fin + ini) >> 1;
		pack1 = funRec(v, w, ini, mitad);
		pack2 = funRec(v, w, mitad + 1, fin);

		if (pack2.coinc || pack2.cruzado) {
			pack1 = pack2;
		}
		else if (!pack1.coinc && !pack1.cruzado && pack1.menor && !pack2.menor) {
			pack1.pos = mitad;
			pack1.cruzado = true;
		}

	}
	return pack1;
}

/*
COSTE: La recurrencia utilizada para la resolución de este ejercicio, ha sido el algoritmo de divide y vencerás.

Ecuación de recurrencia:

Co              si n <= 1
T(n) = {2*T(n/2) + c*n  si n > 1}  a = 2, b = 2, k = 1

Por la resolución general de recurrencias, en este caso por División, el coste de este algoritmo es: T(n) ϵ O(n^k logn) = O(nlogn), ya que a = b^k
*/

bool resuelveCaso() {
	int num_elem, aux;
	vector <int> v1, v2;
	tPack pack;

	cin >> num_elem;

	if (num_elem > 0) {
		for (int i = 0; i < num_elem; ++i) {
			cin >> aux;
			v1.push_back(aux);
		}
		for (int i = 0; i < num_elem; ++i) {
			cin >> aux;
			v2.push_back(aux);
		}

		if (v1[0] > v2[0]) {
			pack.coinc = false;
			pack.pos = -1;
		}
		else if (v1[num_elem - 1] < v2[num_elem - 1]) {
			pack.coinc = false;
			pack.pos = num_elem - 1;
		}
		else {
			pack = funRec(v1, v2, 0, num_elem - 1);
		}

		if (pack.coinc) {
			cout << "SI " << pack.pos << "\n";
		}
		else {
			cout << "NO " << pack.pos << " " << pack.pos + 1 << "\n";
		}
		return true;
	}
	else {
		return false;
	}
}

int main() {
	while (resuelveCaso());
	return 0;
}