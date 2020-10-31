#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int resolver(const vector<int> &v, const int tam) {
	
	int cont = 1;
	int res = v[0];
	int max = 1;
	int i = 1;

	while (i < tam) {
		if (v[i - 1] == v[i]) {
			cont++;
		}
		else cont = 1;

		if (cont > max) {
			max = cont;
			res = v[i];
		}

		i++;
	}

	return res;
}

bool resuelveCaso() {
	int cant, nums;
	vector<int> v;

	cin >> cant;

	if (cant == 0) return false;

	for (int i = 0; i < cant; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	//ordenamos el vector
	sort(v.begin(), v.end());

	cout << resolver(v, cant) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}