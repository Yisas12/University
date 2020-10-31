#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
//{Pre: 0 <= k <= long(v) ^ ordenado(v)}
int resolver(const vector<int> &v, const int tam, const int k) {
	int ret = 1;
	int a = 0, b = 1;

	//{I: (0 <= a < b <= n) ^ ret = max i, j : (0 <= i <= j < b) ^ (v[j] - v[i] < k) : j - i + 1 ^ a = min i : (0 <= i < b) ^ (v[b - 1] - v[a] < k) : i}
	while (b < tam) {

		if (v[b] - v[a] < k) {
			b++;
		}
		else {
			a++;
		}

		ret = max(ret, b-a);
	}

	return ret;
}
//{ Post: ret = max i, j : (0 <= i <= j < tam) ^ (v[j] - v[i] < k) : j - i + 1}

bool resuelveCaso() {
	int tam, nums, k;
	vector<int> v;

	cin >> tam >> k;

	if (tam == 0 && k == 0) return false;

	for (int i = 0; i < tam; i++) {
		cin >> nums;
		v.push_back(nums);
	}

	int p = resolver(v, tam, k);

	cout << p << "\n";
	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}