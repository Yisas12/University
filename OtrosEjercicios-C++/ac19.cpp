/*Equipo: Los FAuLos
Jesús Cencerrado Pedrero (Yisas)
Paula Piñuela Monjas (Paula)
Camila Pérez Alaniz (Camila96)
Sergio Crespillo Campos (Screspil)
*/

#include <iostream>
#include <vector>
using namespace std;

long long int inversiones(vector<long long int>& v, long long ini, long long fin){
	long long int res, mitad, aux, i, vj, vk, tam;
	vector <long long int> w;

	if (fin == ini) {
		res = 0;
	}
	else if ((fin - ini) == 1) {
		res = 0;
		if (v[ini] > v[fin]) {
			++res;
			aux = v[ini];
			v[ini] = v[fin];
			v[fin] = aux;
		}
	}
	else {
		mitad = (ini + fin) >> 1;
		res = inversiones(v,ini,mitad) + inversiones(v, mitad + 1, fin);

		tam = fin - ini + 1;
		w.resize(tam);
		i = 0;
		vj = ini;
		vk = mitad + 1;
		while ((i < tam) && (vj <= mitad) && (vk <= fin)) {
			if (v[vj] <= v[vk]) {
				w[i] = v[vj];
				++vj;
			}
			else {
				w[i] = v[vk];
				res += abs(ini + i - vk);
				++vk;
			}
			++i;
		}

		while (vj <= mitad) {
			w[i] = v[vj];
			++vj;
			++i;
		}
		while (vk <= fin) {
			w[i] = v[vk];
			res += abs(ini + i - vk);
			++vk;
			++i;
		}
		for (i = 0; i < tam; ++i) {
			v[ini + i] = w[i];
		}

	}
	return res;
}

/*Analizando el coste del algoritmo siendo:
 a = 2 el número de llamadas recursivas,
 b = 2 el factor de disminución de los datos, ya que divides el array en 2
 k = 1 el coste de preparación de las llamadas recursivas, ya que las operaciones de preparación son lineales
 el coste en el caso a * T(n/b) + c * nk es, por división, O(n*logn).
 */

void resuelveCaso() {

	long long int num, aux;
	vector<long long int> v;

	cin >> num;

	if (num == 0) {
		cin.get();
		cout << "0\n";
	}
	else {
		for (long long int i = 0; i < num; ++i) {
			cin >> aux;
			v.push_back(aux);
		}
		aux = inversiones(v, 0, v.size() - 1);
		cout << aux << "\n";
	}

}




int main() {

	int numCasos;
	cin >> numCasos;
	for (int i = 0; i < numCasos; ++i) {
		resuelveCaso();
	}

	return 0;
}