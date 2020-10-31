#include<iostream>
#include<vector>
using namespace std;

// P = {tam > 0}
int tamSegmentoMaximo(const vector<int> &v, const int tam) {
	int ret = 1;
	int aux = 1;

	for (int i = 0; i < tam - 1; i++) {
		if (v[i] == v[i + 1]) aux++;
		else aux = 1;

		if (aux > ret) ret = aux;
	}

	return ret;
}
/*Lo que quiero expresar es que ret = al maximo del segmento b-a+1 en el que sus elementos sean iguales
Q = {ret = max a, b : 0 < a <= b < tam : b - a + 1 && v[a] = v[b]}
*/
bool resuelveCaso() {
	int tam, nums;
	vector<int> v;

	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	cout << tamSegmentoMaximo(v, tam) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}