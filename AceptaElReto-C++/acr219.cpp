#include<iostream>
#include<vector>
using namespace std;

int resolver(const vector<int> &v, const int tam) {

	int res = 0;

	for (int i = 0; i < tam; i++) {
		if (v[i] % 2 == 0) res++;
	}

	return res;
}

void resuelveCaso() {
	int cant, nums;
	vector<int> v;

	cin >> cant;

	for (int i = 0; i < cant; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	cout << resolver(v, cant) << "\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}