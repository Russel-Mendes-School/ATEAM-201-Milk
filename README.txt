# ATEAM-201-Milk README

## Student Info
Course: cs400
Semester: Fall 2019
Project name: Milk Weights
Team Memebers:
1. Russel Mendes, Lec 002, rmendes@wisc.edu
2. Akshay Bolda, Lec 001, bolda@wisc.edu
3. Roland Jiang, Lec 002, rjian45@wisc.edu
4. Will Hu, Lec 002, whu72@wisc.edu
5. Ben Rinvelt, Lec 002, Rinvelt@wisc.edu

Russel Mendes, Will Hu, and Roland Jiang were on the same xteam



# FEATURES
* upload a single file or an entire directory of csv files of data
* edit, delete, and add farms from the user interface
* export farms and statistics to files
* run statistics on specific farms in the database such as maximum sales and standard deviation
* a range of farm reports

# User Guide
### Buttons
* EXECUTE: this buttons moves the GUI to the next state
* PURGE: clears the GUI. This also clears up any command selection problems
* EXIT PROGRAM: exits the GUI
### Selection Box
* Load Commands
    * Load File: give the path to the file to load data from
    * Load Dir: load every file in the directory. This function will parse subfolders as well
    * Null Command: This command does nothing but acts as a place holder
* Edit Commands
    * Edit File: edit a file in the GUI
    * Edit Farm: edit a farm in the GUI
    * New Farm: create a new farm from the gui
    * Delete Farm: delete a farm from the database
    * Null Command: This command does nothing but acts as a place holder
* Export Commands
    * Export Farm: exports a given farm to a file designated by the user
    * Export Stats: exports the stats of a given farm to a file designated by the user
    * Show Logs: show the history of commands caused by the user
    * Export Logs: save the log into a list designated by the user
* Stats Commands
    * Max Sales: the max sales of a farm in a given year
    * Min Sales: the min sales of a farm in a given year
    * Avg Sales: the average sales of a farm in a given year
    * Dev Sales: the standard deviation of a farm in a given year
* Asg Commands
    *  Farm Report: create a report of a given farm
    *  Annual Report: create a report for all farms in a given year
    *  Monthly Report: create a report for all farms in a given month
    *  Data Range Report: create a report for all farms in a given data range
### User Text Interface
* The User Text Interface is where the user types to interact with the GUI. This interface is case sensitive to the commands asked


# Download Dependencies
* javafx
* java 11.0.6

# FUTURE FEATURES
* Edit Farm
* New Farm
* Edit File

# BUG REPORT
* TODO

