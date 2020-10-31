#include<iostream>
#include<vector>
using namespace std;

void resolver(const vector<int> &v, const int tam, int &va, int &p) {

	for (int i = 1; i < tam - 1; i++) {
		if (v[i] > v[i - 1] && v[i] > v[i + 1]) p++;
		else if (v[i] < v[i - 1] && v[i] < v[i + 1]) va++;
	}
}

void resuelveCaso() {
	int cant, nums;
	vector<int> v;

	cin >> cant;

	for (int i = 0; i < cant; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	int valles = 0, picos = 0;

	resolver(v, cant, valles, picos);

	cout << picos << " " << valles << "\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}