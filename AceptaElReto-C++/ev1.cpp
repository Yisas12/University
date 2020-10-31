#include<iostream>
#include<vector>
using namespace std;

//Complejidad: O(n) siendo n el número de elementos del vector ya que el vector se recorre una sola vez
//{Pre: 0 <= i < tam && tam >= 0)
int botin(const vector<long long int> &v, const int tam, const int k) {
	int ret = 0;
	int i = 0;
	int j = 1;
	bool encontrado = false;
	long long int suma = v[0];

	/*while(j < tam){
		if(suma < k && (j < n)){
			suma += v[j];
			j++;
		}
		else{
			suma-=v[i];
			i++;
		}

		if(suma <= k){
			ret = max(ret, suma);
		}
	}*/
	//Cota: (i < tam)
	while (i < tam && j < tam && !encontrado) {
		suma += v[j];

		if (suma < k) {
			if (ret < suma) ret = suma;
			j++;
		}
		else if (suma == k) {
			encontrado = true;
			ret = suma;
		}
		else {
			i++;
			suma = v[i];
			j = i + 1;
		}
	}

	return ret;
}

//{Post: ret = max i, j : 0 <= i < j < tam : v[i] + v[j]}
bool resuelveCaso() {
	long long int tam, lingotes;
	vector<long long int> v;
	long long int k;

	cin >> tam;

	if (tam == 0) return false;

	cin >> k;

	for (int i = 0; i < tam; i++) {
		cin >> lingotes;
		v.push_back(lingotes);
	}

	cout << botin(v, tam, k) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}