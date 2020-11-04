#include<iostream>
#include<vector>
using namespace std;

bool resolver(const vector<int> &v, const int tam, const int d) {
	int i = 0;
	int min = v[0];
	int j = 1;

	while (i < tam && j < tam) {
		if (v[i] < v[j]) {
			if (v[j] - min > d) return false;
		}
		else if (v[i] == v[j]) {
			min = v[j];
		}
		else {
			min = v[j];
		}

		i++;
		j++;
	}

	return true;
}

bool resuelveCaso() {
	int d, tam, nums;
	vector<int> v;

	cin >> d >> tam;

	if (!cin)return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	if (resolver(v, tam, d)) cout << "APTA\n";
	else cout << "NO APTA\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}