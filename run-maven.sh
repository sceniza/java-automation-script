#!/usr/bin/env bash

base_url=$1
test_tag=$2

if [[ $test_tag == '' ]]; then
    tags_set='~@ignore'

else
    tags_set=$test_tag

fi

export base_url

mvn test -Dcucumber.filter.tags=$tags_set
