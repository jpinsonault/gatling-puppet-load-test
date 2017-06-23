#!/usr/bin/env bash

set -x

logfile=/var/log/puppetlabs/puppetserver/lsof.txt
rm -rf $logfile

while true ; do
  echo "lsof-timestamp: $(date)" >> $logfile
  lsof -P >> $logfile
  sleep 300
done
