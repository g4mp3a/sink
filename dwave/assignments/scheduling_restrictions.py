# Copyright 2020 D-Wave Systems Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from dimod import DiscreteQuadraticModel
from dwave.system import LeapHybridDQMSampler

def get_token():
    '''Returns personal access token. Only required if submitting to autograder.'''
    
    # TODO: Enter your token here
    return 'Z206-9bdeb213df8aa57d1ad0cc6a81782450bda8c19c'

# Set the solver we're going to use
def set_sampler():
    '''Returns a dimod sampler'''

    sampler = LeapHybridDQMSampler()

    return sampler

# Set employees and preferences
def employee_preferences():
    '''Returns a dictionary of employees with their preferences'''

    preferences = { "Anna": [1,2,3,4],
                    "Bill": [3,2,1,4],
                    "Chris": [4,2,3,1],
                    "Diane": [4,1,2,3],
                    "Erica": [1,2,3,4],
                    "Frank": [3,2,1,4],
                    "George": [4,2,3,1],
                    "Harriet": [4,1,2,3]}

    return preferences

# Create DQM object
def build_dqm():
    '''Builds the DQM for our problem'''

    preferences = employee_preferences()
    num_shifts = 4

    # Initialize the DQM object
    dqm = DiscreteQuadraticModel()

    # Build the DQM starting by adding variables
    for name in preferences:
        dqm.add_variable(num_shifts, label=name)

    # Use linear weights to assign employee preferences
    for name in preferences:
        dqm.set_linear(name, preferences[name])

    # TODO: Restrict Anna from working shift 4
    dqm.set_linear_case("Anna", 3, 100)

    # TODO: Set some quadratic biases to reflect the restrictions in the README.
    # Bill and Frank dont want to work the same shift
    for i in range(4):
        dqm.set_quadratic_case("Bill", i, "Frank", i, 100)
        #dqm.set_quadratic_case("Erica", i, "Harriet", i, -100)
    
    # Erica and Harriet would like to work the same shift
    for i in range(4):
        dqm.set_quadratic_case("Erica", i, "Harriet", i, 0)
        for j in range(i+1, 4):
            dqm.set_quadratic_case("Erica", i, "Harriet", j, 100)
    
    # Each shift has 2 people exactly
    # sum(PersonX|Shift(i)) = 2 -> ( sum(Person|Shift(i)) -2 )**2 = 0
    # ->
    # Linear terms: -3*Person
    # Quadratic terms: sum(sum( 2*PersonX*PersonY ))

    people = list(preferences.keys())
    for i in range(4):
      for j in range(len(people)):
        dqm.set_linear_case(people[j], i, dqm.get_linear_case(people[j],i)-3)
        for k in range(j+1, len(people)):
          dqm.set_quadratic_case(people[j], i, people[k], i, dqm.get_quadratic_case(people[j], i, people[k], i)+2)
        
    
    return dqm

# Solve the problem
def solve_problem(dqm, sampler):
    '''Runs the provided dqm object on the designated sampler'''

    # Initialize the DQM solver
    sampler = set_sampler()

    # Solve the problem using the DQM solver
    sampleset = sampler.sample_dqm(dqm, label='Training - Employee Scheduling')

    return sampleset

# Process solution
def process_sampleset(sampleset):
    '''Processes the best solution found for displaying'''
   
    # Get the first solution
    sample = sampleset.first.sample

    shift_schedule=[ [] for i in range(4)]

    # Interpret according to shifts
    for key, val in sample.items():
        shift_schedule[val].append(key)

    return shift_schedule

## ------- Main program -------
if __name__ == "__main__":

    # Problem information
    shifts = [1, 2, 3, 4]
    num_shifts = len(shifts)

    dqm = build_dqm()

    sampler = set_sampler()

    sampleset = solve_problem(dqm, sampler)

    shift_schedule = process_sampleset(sampleset)

    for i in range(num_shifts):
        print("Shift:", shifts[i], "\tEmployee(s): ", shift_schedule[i])

