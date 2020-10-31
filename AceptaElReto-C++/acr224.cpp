#include<iostream>
#include<vector>
using namespace std;

int resolver(const vector<int> &v, const int tam, bool &si) {
	
	int i = tam - 2;
	int suma = v[tam - 1];
	int max = 0;
	int res = 0;

	while (i >= 0) {
		
		if (suma == v[i]) {
			if (max < suma) {
				max = suma;
				res = i + 1;
			}
			si = true;
		}

		suma += v[i];
		i--;
	}

	return res;
}

bool resuelveCaso() {
	int tam, nums;
	vector<int> v;
	bool si = false;

	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	int p = resolver(v, tam, si);

	if (si) cout << "SI " << p << "\n";
	else cout << "NO\n";

	return true;
}

int main() {
	while (resuelveCaso());

	return 0;
}