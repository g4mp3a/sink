import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import norm, t

# Common values for B are 200 and 500
B = 200
N = 20
# Standard normal with N points. Looking for mu=0 and variance=1.
X = np.random.randn(N)

print("Sample mean of X: %f" % X.mean())

individual_estimates = np.empty(B)
for b in range(B):
    # Default is to sample with replacement
    sample = np.random.choice(X, size=N)
    individual_estimates[b] = sample.mean()

Bmean = individual_estimates.mean()
Bstd = individual_estimates.std()

# Calculating Confidence Interval (CI)
# norm.ppf(0.025) => 95% CI. This is the inverse of cdf and ~ -1.96
bootstrap_lower_limit = Bmean + norm.ppf(0.025)*Bstd
# norm.ppf(0.975) ~ +1.96
bootstrap_upper_limit = Bmean + norm.ppf(0.975)*Bstd

# Traditional way of calculating CI
lower = X.mean() + norm.ppf(0.025)*X.std()/np.sqrt(N)
upper = X.mean() + norm.ppf(0.975)*X.std()/np.sqrt(N)

print("Bootstrap mean of X: %f" % Bmean)

plt.hist(individual_estimates, bins=20)
plt.axvline(x=bootstrap_lower_limit, linestyle='--', color='b', label="Lower bound for 95% CI (bootstrap)")
plt.axvline(x=bootstrap_upper_limit, linestyle='--', color='b', label="Upper bound for 95% CI (bootstrap)")
plt.axvline(x=lower, linestyle='--', color='r', label="Lower bound for 95% CI")
plt.axvline(x=upper, linestyle='--', color='r', label="Upper bound for 95% CI")
plt.legend()
plt.show()



