# -*- coding: utf-8 -*-
"""
Created on Fri May 29 21:09:43 2015

@author: bastian
"""

import numpy as np
import glob
import matplotlib.pyplot as plt

LINE_NUMBER = 0
EUCLEDIAN_DISTANCE = 1
DELIVERY_TIME = 2

class Result:
    def __init__(self, success, path, times, number_of_expansions):
        self._success = success
        self._path = path
        self._times = times
        self._number_of_expansions = number_of_expansions

class City:
    def __init__(self, x, y, time):
        self.x = x
        self.y = y
        self.time = time
        
    def __str__(self):
        return "city:(%f|%f), time: %f" % (self.x, self.y, self.time)
        
def readCities(filename):
    with open(filename) as f:
        content = f.readlines()
    
    cities = []
    for line in content:
        elements = line.split(' ')
        x = float(elements[0])
        y = float(elements[1].translate(None, '\n'))
        if(len(elements) == 3):
            times = float(elements[2].translate(None, '\n'))
        else:
            times= 0.0
        cities.append(City(x,y,times))
    return cities
     
def compute_distance(city_one, city_two):
    x_one = city_one.x
    x_two = city_two.x
    y_one = city_one.y
    y_two = city_two.y
    x_distance = abs(x_one - x_two)
    y_distance = abs(y_one - y_two)
    return np.sqrt(pow(x_distance, 2) + pow(y_distance, 2))
     
def compute_time_for_path(current_path):
    distance = 0.0
    first_city = current_path[0]
    for i in range(1, len(current_path)):
        second_city = current_path[i]
        distance += compute_distance(first_city, second_city)
        first_city = second_city
    # time equals distance
    return distance

def sort_fringe(current_element, fringe, order):
    if(order == LINE_NUMBER):
        return fringe[:]
    elif(order == EUCLEDIAN_DISTANCE):
        # eucledian distance
        distances = []
        for i in range(len(fringe)):
            distances.append(compute_distance(fringe[i], current_element))
        sort_order = np.argsort(distances)
        return np.array(fringe)[sort_order].tolist()
    elif(order == DELIVERY_TIME):
        # delivery time
        times = []
        for i in range(len(fringe)):
            times.append(fringe[i].time)
        sort_order = np.argsort(times)
        return np.array(fringe)[sort_order].tolist()
        

def solve(fringe, current_element, current_path, times_taken, order, number_of_expansions):
    # Check if solved or failed
    current_path.append(current_element)
    time_taken = compute_time_for_path(current_path)
    times_taken.append(time_taken)
    if(time_taken > current_element.time):
        return Result(False, [], [], number_of_expansions) 
    if(len(fringe) == 0):
        return Result(True, current_path, times_taken, number_of_expansions)
    
    fringe = sort_fringe(current_element, fringe[:], order)
    #print 'sorted fringe: '
    #for city in fringe:
    #    print city
    for city in fringe:
        number_of_expansions += 1
        next_element = city
        resulting_fringe = fringe[:]
        resulting_fringe.remove(city)
        next_current_path = current_path[:]
        result = solve(resulting_fringe, next_element, next_current_path, times_taken[:], order, number_of_expansions)
        if(result._success):
            return result
    return Result(False, [], [], number_of_expansions)

def solve_file_using_single_order(filename, order):
    cities = readCities(filename)  
    if(len(cities) > 0):
        firstCity = cities[0]
        cities.remove(firstCity)
        result = solve(cities, firstCity, [], [], order, 1)
        if(result._success):
            #print 'solution:'
            #for city in result._path:
            #    print city
            #print 'times taken:'
            #for time in result._times:
            #    print time
            print 'time taken: ', result._times[-1]
            print 'number of expansions: ', result._number_of_expansions
            return result
        else:
            print 'Problem could not be solved. Made %d expansions' % (result._number_of_expansions)
    return Result(True, [], [], 0)

def solve_file(filename):
    results = []
    results.append(solve_file_using_single_order(filename, LINE_NUMBER))
    results.append(solve_file_using_single_order(filename, EUCLEDIAN_DISTANCE))
    results.append(solve_file_using_single_order(filename, DELIVERY_TIME))
    return results
    
def plot_path(path, title):
    fig = plt.figure()
    fig.suptitle(title)
    ax = fig.add_subplot(111)
    for i in range(len(path)-1):
        ax.plot(np.array([path[i].x,path[i+1].x]), np.array([path[i].y, path[i+1].y]), '-')
    plt.show()

def solve_all_files_in_directory():    
    files = glob.glob("scenarios/*.txt")
    for filename in files:
        print '--------------------------------------------------'
        print '---------%s----------------' % (filename)
        print '--------------------------------------------------'
        result = solve_file(filename)
        label0 = '%s_%d' % (filename, 0)
        label1 = '%s_%d' % (filename, 1)
        label2 = '%s_%d' % (filename, 2)
        plot_path(result[0]._path, label0)
        plot_path(result[1]._path, label1)
        plot_path(result[2]._path, label2)
        
solve_all_files_in_directory()
    