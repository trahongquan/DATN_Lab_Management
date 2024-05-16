#!/bin/sh

# wait-for-it.sh - Script to wait for a database to be ready

# Usage: ./wait-for-it.sh host:port -- command args
# Example: ./wait-for-it.sh mysql-container-lab:3306 -- java -jar app.jar

set -e

TIMEOUT=15
QUIET=0

usage() {
    echo "Usage: $0 host:port [-- command args]"
    echo "Options:"
    echo "  -t TIMEOUT    Timeout in seconds, default is 15 seconds"
    echo "  -q            Quiet mode, suppress output"
    exit 1
}

wait_for() {
    while ! nc -z $HOST $PORT; do
        if [ $QUIET -eq 0 ]; then
            echo "Waiting for $HOST:$PORT..."
        fi
        sleep 1
    done
}

# Parse arguments
while [ $# -gt 0 ]; do
    case "$1" in
        *:* )
        HOSTPORT="$1"
        shift 1
        ;;
        -t)
        TIMEOUT="$2"
        if [ "$TIMEOUT" = "" ]; then
            usage
        fi
        shift 2
        ;;
        -q)
        QUIET=1
        shift 1
        ;;
        --)
        shift
        CMD="$@"
        break
        ;;
        *)
        usage
        ;;
    esac
done

if [ "$HOSTPORT" = "" ]; then
    echo "Error: you need to provide a host and port to wait for"
    usage
fi

HOST=$(echo $HOSTPORT | cut -d : -f 1)
PORT=$(echo $HOSTPORT | cut -d : -f 2)

if [ "$CMD" = "" ]; then
    echo "Error: you need to provide a command to execute after waiting"
    usage
fi

wait_for

if [ $QUIET -eq 0 ]; then
    echo "$HOST:$PORT is available"
fi

exec $CMD
