#imports here
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
import time
from selenium.webdriver.chrome.options import Options
from selenium.webdriver import DesiredCapabilities
import datetime
import sys

def get_date(input_date):
    
    #for m and h
    if(len(input_date) == 2):
        if(input_date[1]=='h' or input_date[1]=='m'):
            date = datetime.datetime.now()
            date = str(date.day)+'-'+ str(date.month)+'-'+ str(date.year)
            return date
    if(len(input_date) == 3):
        if(input_date[2]=='h' or input_date[2]=='m'):
            date = datetime.datetime.now()
            date = str(date.day)+'-'+ str(date.month)+'-'+ str(date.year)
            return date

    #for Day
    if(len(input_date) == 2 and input_date[1]=='d'):
        date = datetime.datetime.now()
        date = str(date.day-int(input_date[0]))+'-'+ str(date.month)+'-'+ str(date.year)
        return date

    #for Month
    if(len(input_date.split(' ')[1]) == 3):
        date = datetime.datetime.now()
        months = (None, 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec')
        integervalue = months.index(input_date.split(' ')[1])
        date = input_date.split(' ')[0]+'-'+str(integervalue)+'-'+ str(date.year)
        return date
        
chrome_exe_path = "C:\\Users\\smk11\\Desktop\\chromedriver_win32\\chromedriver.exe"

# For headless chrome
"""chrome_options = Options()
chrome_options.add_argument("--headless")

capabilities = DesiredCapabilities.CHROME.copy()
capabilities['acceptSslCerts'] = True 
capabilities['acceptInsecureCerts'] = True
driver = webdriver.Chrome(chrome_options = chrome_options,executable_path=chrome_exe_path,desired_capabilities=capabilities)"""

#driver = webdriver.Chrome(executable_path=chrome_exe_path)
driver = webdriver.Chrome("chromedriver.exe")

try:
    username = sys.argv[1]
    profilePage = "https://www.kooapp.com/profile/" + username
    driver.get(profilePage)

    time.sleep(2)
    driver.execute_script("window.scrollTo(0, 12000);")

    post_captions = driver.find_elements_by_class_name('_3NfMI')
    post_times = driver.find_elements_by_class_name('_3AAWv')


    data = []
    for post, timing in zip(post_captions, post_times):
        try:
            data.append({"data":post.text, "date":get_date(timing.text)})
        except:
            pass

    print(data)
    """
    OUTPUT : 
        data = [
            {"data": "adas asd ....", "date":"..."},
            {}, ...
        ]

    """
except:
    print ("Error")
finally:
    driver.quit()