#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int resolver(const vector<int> &v, const int tam) {
	int ret = 0;
	int sum1 = 0, sum2 = 0;

	for (int i = 0; i < tam; i++) {
		sum1 += v[i];
	}
	
	if (sum1 == sum2) ret++;

	for (int i = 0; i < tam; i++) {
		sum1 -= v[i];
		sum2 += v[i];

		if (sum1 == sum2) ret++;
	}

	return ret;
}

bool resuelveCaso() {
	int tam, nums;
	vector<int> v;

	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	int p = resolver(v, tam);

	cout << p << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}