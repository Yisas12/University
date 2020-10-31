#include<iostream>
#include<vector>
using namespace std;

int resolver(const vector<int> &v, const int tam, int suma) {
	int res = 0;
	int i = 0;
	int aux = suma, aux2 = 0;
	int s = suma;

	while (i < tam) {
		aux -= v[i];
		aux2 += v[i];

		if (abs(aux2 - aux) < abs(s)) {
			res = i + 1;
			s = abs(aux2 - aux);
		}

		i++;
	}

	return res;
}

bool resuelveCaso() {
	int tam, nums;
	int sum = 0, rest = 0;
	vector<int> v;

	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;

		if (nums >= 0) sum += nums;
		else rest += nums;

		v.push_back(nums);
	}

	if (sum + rest == 0) cout << "0\n";
	else {
		int p = resolver(v, tam, sum + rest);

		cout << p << "\n";
	}
	
	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}